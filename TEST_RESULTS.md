# Test Results

Total: 66 | Passed: 66 | Failed: 0

| ID | Storage | What is tested (expected result) | Result |
|---|---|---|---|
| TC-01 | disk | Create file: name, type, size, owner saved; content can be read back | PASS |
| TC-02 | disk | File info has a creation date | PASS |
| TC-03 | disk | List shows only own files (Bob sees none until shared) | PASS |
| TC-04 | disk | Search by name: part of the name, ignores upper/lower case, only visible files | PASS |
| TC-05 | disk | Rename works and is saved | PASS |
| TC-06 | disk | Delete removes the file and its content | PASS |
| TC-07 | disk | Bad file names are rejected (empty, spaces, / , |, longer than 50) | PASS |
| TC-08 | disk | Unknown type and too long content are rejected | PASS |
| TC-09 | disk | Same name twice: rejected for the same owner, allowed for another owner | PASS |
| TC-10 | disk | Unknown file id gives an error | PASS |
| TC-11 | disk | Create folder and list it; same name twice is rejected; others do not see it | PASS |
| TC-12 | disk | Move file into a folder, see it in the folder contents, move it back to root | PASS |
| TC-13 | disk | Folder with files cannot be deleted (our rule); empty folder can | PASS |
| TC-14 | disk | Another user cannot open, delete or move files into my folder | PASS |
| TC-15 | disk | Owner can read, rename, share and delete | PASS |
| TC-16 | disk | Viewer can read a shared file (content is correct) | PASS |
| TC-17 | disk | Viewer cannot rename (DENIED) and the name stays the same | PASS |
| TC-18 | disk | Viewer cannot delete (DENIED) and the file is still there | PASS |
| TC-19 | disk | Viewer cannot share (DENIED) and cannot move | PASS |
| TC-20 | disk | User without access: read, rename, delete are DENIED and the file is not listed | PASS |
| TC-21 | disk | Unknown user is DENIED | PASS |
| TC-22 | disk | Share errors: unknown user, owner himself, already shared | PASS |
| TC-23 | disk | After restart: file, content, sharing and folder are still there | PASS |
| TC-24 | disk | New ids are never reused (even after delete and restart) | PASS |
| TC-25 | disk | Content is really saved by the chosen storage (disk) | PASS |
| TC-40 | disk | Edit creates version 2; current content is the new one, version 1 can still be read | PASS |
| TC-41 | disk | Rollback to version 1 makes version 3 (old versions are kept) | PASS |
| TC-42 | disk | Viewer can read old versions but cannot edit or roll back (DENIED) | PASS |
| TC-43 | disk | Same content and unknown version number are rejected; no new version is made | PASS |
| TC-44 | disk | Versions are still there after restart | PASS |
| TC-01 | single | Create file: name, type, size, owner saved; content can be read back | PASS |
| TC-02 | single | File info has a creation date | PASS |
| TC-03 | single | List shows only own files (Bob sees none until shared) | PASS |
| TC-04 | single | Search by name: part of the name, ignores upper/lower case, only visible files | PASS |
| TC-05 | single | Rename works and is saved | PASS |
| TC-06 | single | Delete removes the file and its content | PASS |
| TC-07 | single | Bad file names are rejected (empty, spaces, / , |, longer than 50) | PASS |
| TC-08 | single | Unknown type and too long content are rejected | PASS |
| TC-09 | single | Same name twice: rejected for the same owner, allowed for another owner | PASS |
| TC-10 | single | Unknown file id gives an error | PASS |
| TC-11 | single | Create folder and list it; same name twice is rejected; others do not see it | PASS |
| TC-12 | single | Move file into a folder, see it in the folder contents, move it back to root | PASS |
| TC-13 | single | Folder with files cannot be deleted (our rule); empty folder can | PASS |
| TC-14 | single | Another user cannot open, delete or move files into my folder | PASS |
| TC-15 | single | Owner can read, rename, share and delete | PASS |
| TC-16 | single | Viewer can read a shared file (content is correct) | PASS |
| TC-17 | single | Viewer cannot rename (DENIED) and the name stays the same | PASS |
| TC-18 | single | Viewer cannot delete (DENIED) and the file is still there | PASS |
| TC-19 | single | Viewer cannot share (DENIED) and cannot move | PASS |
| TC-20 | single | User without access: read, rename, delete are DENIED and the file is not listed | PASS |
| TC-21 | single | Unknown user is DENIED | PASS |
| TC-22 | single | Share errors: unknown user, owner himself, already shared | PASS |
| TC-23 | single | After restart: file, content, sharing and folder are still there | PASS |
| TC-24 | single | New ids are never reused (even after delete and restart) | PASS |
| TC-25 | single | Content is really saved by the chosen storage (single) | PASS |
| TC-40 | single | Edit creates version 2; current content is the new one, version 1 can still be read | PASS |
| TC-41 | single | Rollback to version 1 makes version 3 (old versions are kept) | PASS |
| TC-42 | single | Viewer can read old versions but cannot edit or roll back (DENIED) | PASS |
| TC-43 | single | Same content and unknown version number are rejected; no new version is made | PASS |
| TC-44 | single | Versions are still there after restart | PASS |
| TC-30 | - | Bridge: a new storage (FakeStorage) works with the unchanged FileManager | PASS |
| TC-31 | - | Proxy: Factory gives the client a FileProxy, not the FileManager | PASS |
| TC-32 | - | Unknown storage name gives an error | PASS |
| TC-33 | - | Damaged metadata file gives an error (no crash) | PASS |
| TC-34 | - | Storages: missing content gives an error; key like ../evil is refused | PASS |
| TC-45 | - | Versions: 3 versions are stored; deleting the file removes all of them | PASS |
