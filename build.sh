TAG="${1#refs/tags}"
BIN_VER="null"
VERSION_PATTERN="v([0-9.]){5}[+](release[.][0-9]|debug)"

# $1 Key | $2 Value
function set_output() {
    echo "$1=$2" >> $GITHUB_OUTPUT
}

function update_binary_version() {
    NEW_VERSION=$1
    sed -i'' -E "s#$VERSION_PATTERN#$NEW_VERSION#g" gradle.properties
}

function get_bin_ver() {
    # PROP=$(cat gradle.properties)
    BIN_VER=$(grep -Eo "$VERSION_PATTERN" gradle.properties)
    echo "Got version: $BIN_VER"
}

echo "Updating binary version"
if [[ "$TAG" != refs/tags/v* ]]; then
    get_bin_ver
    REAL=${BIN_VER//(release[.][0-9]|debug)/debug}
    update_binary_version $REAL
    get_bin_ver
else
    # set_binary_build_type "release"
    update_binary_version $TAG
    get_bin_ver
fi
echo "New version: $BIN_VER"


echo "Building with GradleW"
./gradlew build

echo "Setting output: binver=$BIN_VER"
set_output "binver" $BIN_VER
