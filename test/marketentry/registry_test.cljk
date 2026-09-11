(ns marketentry.registry-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.registry :as registry]))

(deftest engagement-fee-recompute
  (let [e {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 860000.0}]
    (is (== 860000.0 (registry/compute-engagement-fee e)))
    (is (true? (registry/engagement-fee-matches-claim? e))))
  (let [bad {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 999000.0}]
    (is (false? (registry/engagement-fee-matches-claim? bad)))))

(deftest register-draft-and-submit
  (let [d (registry/register-draft "eng-1" "MKD" 0)
        s (registry/register-submit "eng-1" "MKD" 0)]
    (is (= "MKD-DFT-000000" (get d "draft_number")))
    (is (= "MKD-SUB-000000" (get s "submit_number")))
    (is (nil? (get-in d ["certificate" "proof"])))
    (is (= "draft-unsigned" (get-in s ["certificate" "status"])))))

(deftest register-requires-ids
  (is (thrown? Exception (registry/register-draft "" "MKD" 0)))
  (is (thrown? Exception (registry/register-submit "eng-1" "" 0))))

(deftest public-charges-arrears-violation-requires-both-conditions-to-cure
  (testing "ЗЈН Член 88(2)(a)'s cure is CONJUNCTIVE -- approval alone, without current regular payment, does NOT cure"
    (is (true? (registry/public-charges-arrears-violation?
                {:annual-turnover 1200000 :tax-arrears-amount 8000
                 :tax-deferred-payment-approved? true
                 :tax-deferred-payment-current? false}))
        "approved but NOT currently paying regularly -> still violates"))
  (testing "no deferral requested at all -> violates"
    (is (true? (registry/public-charges-arrears-violation?
                {:annual-turnover 2000000 :tax-arrears-amount 1
                 :tax-deferred-payment-approved? false
                 :tax-deferred-payment-current? false}))))
  (testing "BOTH approved AND currently paying regularly -> cured, no violation"
    (is (false? (registry/public-charges-arrears-violation?
                {:annual-turnover 1200000 :tax-arrears-amount 8000
                 :tax-deferred-payment-approved? true
                 :tax-deferred-payment-current? true}))))
  (testing "no arrears declared -> never violates, regardless of deferral flags"
    (is (false? (registry/public-charges-arrears-violation? {:annual-turnover 2000000})))
    (is (false? (registry/public-charges-arrears-violation?
                {:annual-turnover 2000000 :tax-arrears-amount 0
                 :tax-deferred-payment-approved? false
                 :tax-deferred-payment-current? false})))))
