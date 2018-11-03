xquery version "3.1";

module namespace c ="http://exist-db.org/extensions/strings/codec/test";

declare namespace test="http://exist-db.org/xquery/xqsuite";

import module namespace code = "http://exist-db.org/extensions/strings/codec"
                at "java:org.exist.extensions.strings.CodecModule";

declare
    %test:arg("value", "Robert") %test:assertEquals("R163")
    %test:arg("value", "Rupert") %test:assertEquals("R163")
    %test:arg("value", "Rubin") %test:assertEquals("R150")
    %test:arg("value", "Ashcraft") %test:assertEquals("A261")
    %test:arg("value", "Tymczak") %test:assertEquals("T522")
    %test:arg("value", "Pfister") %test:assertEquals("P236")
    %test:arg("value", "Honeyman") %test:assertEquals("H555")
function c:soundex($value as xs:string) {
    codec:soundex($value)
};

declare
    %test:arg("value", "Bishop") %test:assertEquals("BASAP")
    %test:arg("value", "Chapman") %test:assertEquals("CAPNAN")
    %test:arg("value", "Franklin") %test:assertEquals("FRANCL")
function c:nysiis($value as xs:string) {
    codec:nysiis($value)
};

declare
    %test:arg("value", "four") %test:assertEquals("FR")
    %test:arg("value", "twenty") %test:assertEquals("TWNT")
function c:metaphone($value as xs:string) {
    codec:metaphone($value)
};

declare
    %test:arg("value", "Müller-Lüdenscheidt") %test:assertEquals("65752682")
    %test:arg("value", "Harry") %test:assertEquals("07")
    %test:arg("value", "Hari") %test:assertEquals("07")
function c:cologne-phonetic($value as xs:string) {
    codec:cologne-phonetic($value)
};
