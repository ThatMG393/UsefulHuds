TAG="${1#refs/tags}"
BIN_VER="null"

# $1 Key | $2 Value
function set_output() {
    echo "$1=$2" >> $GITHUB_OUTPUT
}

function update_binary_version() {
    NEW_VERSION=$1
    VERSION_PATTERN="v([0-9.]){5}[+](release[.][0-9]|debug)"

    sed -i'' -E "s#$VERSION_PATTERN#$NEW_VERSION#g" gradle.properties
}

function get_bin_ver() {
    BIN_VER=$(cat gradle.properties | grep -Eo $VERSION_PATTERN)
}

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

./gradlew build

set_output "binver" $BIN_VER
