# Summary Report - my moldcell (unauthenticated area)

**Device:** Redmi Note 12S, Android 14 (API 34)
**App:** my moldcell, version 1.43.1 (Google Play)

## Tested
App launch (online and offline), login screen, key elements (inputs, buttons),
inline validation for empty phone/email and empty password, error dialog for
valid credentials + wrong password, OK button closes dialog, login attempt
offline, back navigation, TalkBack labels.

## Out of scope
Authenticated area, registration E2E, password recovery E2E, payments,
plan changes, push notifications, full accessibility audit.

## Defects
- BUG-001: 'Login' button stays enabled with empty fields (Minor / Medium)
- Risk: login format validation may be too permissive

## What I would test next
Registration flow, password recovery, Wi-Fi <-> LTE switching, localization
(RU/RO/EN), full accessibility (TalkBack).

## Automation
7 tests (3 positive, 4 negative), all pass on Xiaomi Android 14, ~19 s total.
Configuration and test data externalized, locators centralized.

## Time spent
Manual ~80 min, Automation ~80 min, Report ~20 min.
