# Testing - Option 1 - Version History

## How we test
* No test library: `Tests.java` is a normal Java program with a method `check(id, text, true/false)`.
* Run `./test.sh`. It prints PASS / FAIL for every check and writes the table to [TEST_RESULTS.md](TEST_RESULTS.md).
* Every test group runs **twice, once for each storage** (`disk` and `single`). So the same tests prove that the Bridge works with both storages.
* The tests talk to the program through the **proxy**, like a real user.
* Before each group the test folder is deleted, so tests do not depend on each other.

## Test cases
| ID | What is tested | Expected |
|---|---|---|
| TC-01..06 | create, creation date, list, search, rename, delete | works, data correct, saved |
| TC-07..10 | bad names, bad type / big content, duplicate names, unknown id | rejected with an error message |
| TC-11..14 | folders: create, move, delete rule, other user's folder | works / rejected as our rule says |
| TC-15..22 | permissions: owner, viewer (read ok; rename, delete, share denied), no access, unknown user, share errors | allowed or `DENIED` |
| TC-23..24 | restart: data and ids still there | yes |
| TC-25 | content is saved by the chosen storage | yes |
| TC-30 | Bridge: a third storage works with unchanged `FileManager` | yes |
| TC-31 | Proxy: client gets `FileProxy`, not `FileManager` | yes |
| TC-32..34 | unknown storage, damaged metadata file, storage errors | error message, no crash |
| unique | TC-40 ... TC-45: new version on edit, rollback, viewer rights, invalid edits, restart, delete removes all versions | pass |

## Manual test (menu)
1. `./run.sh disk`, login `alice`, `1` create file `report` / `TXT` / any text.
2. `8` share `f1` with `bob`, then `14` switch to `bob`.
3. `4` read `f1` -> text shown, screen prints `[PROXY] bob wants READ f1 (role VIEWER) -> ALLOWED`.
4. As bob try `6` rename, `7` delete, `8` share -> `ACCESS DENIED`.
5. Switch to `carol`, `4` read `f1` -> `ACCESS DENIED`. Exit, start again: the data is still there.
