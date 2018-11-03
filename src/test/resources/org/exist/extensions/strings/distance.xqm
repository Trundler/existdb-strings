xquery version "3.1";

module namespace d ="http://exist-db.org/extensions/strings/distance/test";

declare namespace test="http://exist-db.org/xquery/xqsuite";

import module namespace code = "http://exist-db.org/extensions/strings/distance"
                at "java:org.exist.extensions.strings.DistanceModule";

(: to test
 : distance.apply(null, *)             = IllegalArgumentException
 : distance.apply(*, null)             = IllegalArgumentException
 :)
declare
    %test:arg("left", "") %test:arg("right", "") %test:assertEquals(0)
    %test:arg("left", "") %test:arg("right", "a") %test:assertEquals(1)
    %test:arg("left", "aaapppp") %test:arg("right", "") %test:assertEquals(7)
    %test:arg("left", "frog") %test:arg("right", "fog") %test:assertEquals(1)
    %test:arg("left", "fly") %test:arg("right", "ant") %test:assertEquals(3)
    %test:arg("left", "elephant") %test:arg("right", "hippo") %test:assertEquals(7)
    %test:arg("left", "hippo") %test:arg("right", "elephant") %test:assertEquals(7)
    %test:arg("left", "hippo") %test:arg("right", "zzzzzzzz") %test:assertEquals(8)
    %test:arg("left", "hello") %test:arg("right", "hallo") %test:assertEquals(1)
function d:levenshtein($left as xs:string, $right as xs:string) {
    distance:levenshtein($left, $right)
};

(: to test: if either input is null or if they do not have the same length  :)
declare
    %test:arg("left", "") %test:arg("right", "") %test:assertEquals(0)
    %test:arg("left", "pappa") %test:arg("right", "pappa") %test:assertEquals(0)
    %test:arg("left", "1011101") %test:arg("right", "1011111") %test:assertEquals(1)
    %test:arg("left", "ATCG") %test:arg("right", "ACCC") %test:assertEquals(2)
    %test:arg("left", "karolin") %test:arg("right", "kerstin") %test:assertEquals(3)
function d:hamming($left as xs:string, $right as xs:string) {
    distance:hamming($left, $right)
};

(: to do: add tests :)
declare
    %test:arg("left", "") %test:arg("right", "") %test:assertError("Invalid text")
    %test:arg("left", "pappa") %test:arg("right", "mamma") %test:assertEquals(1)
function d:cosine($left as xs:string, $right as xs:string) {
    distance:cosine($left, $right)
};