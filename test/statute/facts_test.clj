(ns statute.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is testing]]
            [statute.facts :as facts]))

(deftest mkd-has-spec-basis
  (let [sb (facts/spec-basis "MKD")]
    (is (= 5 (count sb)))
    (is (every? #(str/starts-with? (:statute/url %) "https://") sb))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["MKD" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= 2 (count (facts/by-topic "MKD" :tax))))
  (is (= 1 (count (facts/by-topic "MKD" :labor))))
  (is (= 1 (count (facts/by-topic "MKD" :foreign-investment))))
  (is (= 1 (count (facts/by-topic "MKD" :corporate-governance))))
  (is (empty? (facts/by-topic "MKD" :environment)))
  (is (empty? (facts/by-topic "ATL" :labor))))
