(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest mkd-has-spec-basis
  (let [sb (facts/spec-basis "MKD")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (some? (facts/rep-spec-basis "MKD")))
    (is (some? (facts/corporate-number-spec-basis "MKD")))
    (is (some? (facts/registration-spec-basis "MKD")))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "MKD")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "MKD" all)))
    (is (not (facts/required-evidence-satisfied? "MKD" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["MKD" "USA" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 2 (:covered c)))
    (is (= ["ATL"] (:missing-jurisdictions c)))))

(deftest registration-spec-basis-is-separate-from-corporate-number
  (testing "ЕМБС (company registration, Central Registry) is honestly modeled as a SEPARATE citation from ЕДБ (tax identity, УЈП), not the same number under two names"
    (let [reg (facts/registration-spec-basis "MKD")
          corp (facts/corporate-number-spec-basis "MKD")]
      (is (some? reg))
      (is (some? corp))
      (is (not= (:registration-owner-authority reg) (:corporate-number-owner-authority corp)))
      (is (nil? (facts/registration-spec-basis "ATL")))
      (is (nil? (facts/corporate-number-spec-basis "ATL"))))))
