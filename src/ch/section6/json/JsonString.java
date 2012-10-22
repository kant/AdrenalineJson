/*
 * Copyright (c) 2012, Martin Roth (mhroth@section6.ch)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the AdrenalineJson nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package ch.section6.json;

import java.util.ArrayList;
import java.util.List;

public class JsonString extends JsonValue {

  private final String string;
  
  public JsonString(String string) {
    this.string = string;
  }

  @Override
  public Type getType() {
    return Type.STRING;
  }
  
  @Override
  public String asString() {
    return string;
  }

  @Override
  public Number asNumber() {
    try {
      return Double.parseDouble(string);
    } catch (NumberFormatException e) {
      throw new JsonCastException(e);
    }
  }

  @Override
  protected List<String> toStringArray() {
    ArrayList<String> array = new ArrayList<String>();
    array.add(toString());
    return array;
  }
  
  @Override
  public String toString() {
    // escape all illegal JSON string sequences
    return "\"" + string.replace("\"", "\\\"").replace("\t", "\\t").replace("\n", "\\n")
        .replace("\f", "\\f").replace("\r", "\\r").replace("\\", "\\\\").replace("/", "\\/")
        .replace("\b", "\\b") + "\"";
  }

}
