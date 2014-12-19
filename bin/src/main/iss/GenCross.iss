; -- Example1.iss --
; Demonstrates copying 3 files and creating an icon.

; SEE THE DOCUMENTATION FOR DETAILS ON CREATING .ISS SCRIPT FILES!

[Setup]
AppName=GenCross
AppVerName=GenCross version ${pom.version}
DefaultDirName={pf}\GenCross
DefaultGroupName=GenCross
Compression=lzma
SolidCompression=yes
OutputDir=${project.basedir}\target
OutputBaseFilename=GenCrossUI-setup
UninstallDisplayName=GenCross
ChangesAssociations=yes

[Files]
Source: "${project.basedir}\target\gencross-ui.exe"; DestDir: "{app}"
Source: "${project.basedir}\target\repository\*"; DestDir: "{app}\repository"

[Icons]
Name: "{group}\GenCross"; Filename: "{app}\gencross-ui.exe"
Name: "{group}\Uninstall"; Filename: "{uninstallexe}"

[Registry]
Root: HKCR; Subkey: ".gcr"; ValueType: string; ValueName: ""; ValueData: "GCR"; Flags: uninsdeletevalue
Root: HKCR; Subkey: "GCR"; ValueType: string; ValueName: ""; ValueData: "GenCross"; Flags: uninsdeletekey
Root: HKCR; Subkey: "GCR\DefaultIcon"; ValueType: string; ValueName: ""; ValueData: "{app}\gencross-ui.exe,0"
Root: HKCR; Subkey: "GCR\shell\open\command"; ValueType: string; ValueName: ""; ValueData: """{app}\gencross-ui.exe"" ""%1"""

[UninstallDelete]
Type: filesandordirs; Name: "{app}\repository"

