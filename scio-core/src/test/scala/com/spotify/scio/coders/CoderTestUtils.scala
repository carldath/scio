/*
 * Copyright 2019 Spotify AB.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.spotify.scio.coders

import org.apache.beam.sdk.coders.{Coder => BCoder}
import org.apache.beam.sdk.util.CoderUtils

object CoderTestUtils {

  def testRoundTrip[T](coder: BCoder[T], value: T): Boolean =
    testRoundTrip(coder, coder, value)

  def testRoundTrip[T](writer: BCoder[T], reader: BCoder[T], value: T): Boolean = {
    val bytes = CoderUtils.encodeToByteArray(writer, value)
    val result = CoderUtils.decodeFromByteArray(reader, bytes)
    result == value
  }
}
