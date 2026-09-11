# cloud-itonami-iso3166-mkd

Open ISO 3166 Blueprint for **MKD**: North Macedonia.

- Procurement: Биро за јавни набавки (Bureau for Public Procurement) /
  Електронски систем за јавни набавки (ЕСЈН, e-nabavki.gov.mk)
- Business/tax: Централен регистар (Central Registry, ЕМБС company
  registration) + Управа за јавни приходи (Public Revenue Office, ЕДБ
  tax identity) — two separate authorities, two separate numbers
- General compliance: Закон за работните односи (labour), Закон за
  трговските друштва (company law), Закон за данокот на добивка /
  Закон за данокот на додадена вредност (tax), Закон за финансиска
  поддршка на инвестициите (foreign investment)

AGPL-3.0-or-later.

## Actuation

`:filing/draft` and `:filing/submit` are the two real-world acts this
actor performs (preparing/submitting an ЕСЈН portal filing) — neither
ever auto-commits at any rollout phase, and both always reach a human
market-entry operator for approval, even when the Market-Entry
Compliance Governor is otherwise clean (see `marketentry.phase` /
`marketentry.governor`).

## Flagship governor check

ЗЈН (Закон за јавните набавки, Law on Public Procurement) Член 88(2)(a)
excludes an economic operator with unpaid taxes, contributions or other
public charges UNLESS the operator has been GRANTED an approved
deferred-payment arrangement AND is CURRENTLY paying it regularly — a
CONJUNCTIVE two-condition cure, not a single "approved" flag. Approval
alone does not cure the exclusion under North Macedonia's own statute.
See `marketentry.registry/public-charges-arrears-violation?` and the
`:public-charges-arrears-uncured` governor rule.

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for North Macedonia:

- `src/culture/facts.cljk` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.
