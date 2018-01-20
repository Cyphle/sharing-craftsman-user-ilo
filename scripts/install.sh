#!/usr/bin/env bash

parse_yaml() {
    local prefix=$2
    local s
    local w
    local fs
    s='[[:space:]]*'
    w='[a-zA-Z0-9_]*'
    fs="$(echo @|tr @ '\034')"
    sed -ne "s|^\($s\)\($w\)$s:$s\"\(.*\)\"$s\$|\1$fs\2$fs\3|p" \
        -e "s|^\($s\)\($w\)$s[:-]$s\(.*\)$s\$|\1$fs\2$fs\3|p" "$1" |
    awk -F"$fs" '{
    indent = length($1)/2;
    vname[indent] = $2;
    for (i in vname) {if (i > indent) {delete vname[i]}}
        if (length($3) > 0) {
            vn=""; for (i=0; i<indent; i++) {vn=(vn)(vname[i])("_")}
            printf("%s%s%s=(\"%s\")\n", "'"$prefix"'",vn, $2, $3);
        }
    }' | sed 's/_=/+=/g'
}

# Example ./update-dockerfile.sh user-infos.yml Dockerfile docker-compose.yaml
source=$1
target=$2
composeTarget=$3

ymldata="$(parse_yaml $source)"

infos=(${ymldata// / })

# DATABASE
hostEntry='user_database_host'
userEntry='user_database_user'
passwordEntry='user_database_password'
portEntry='user_database_port'
databaseNameEntry='user_database_name'

hostLength=$((${#infos[0]} - ${#hostEntry} - 5))
userLength=$((${#infos[1]} - ${#userEntry} - 5))
passwordLength=$((${#infos[2]} - ${#passwordEntry} - 5))
portLength=$((${#infos[3]} - ${#portEntry} - 5))
databaseNameLength=$((${#infos[4]} - ${#databaseNameEntry} - 5))

host=${infos[0]:${#hostEntry} + 3:hostLength}
user=${infos[1]:${#userEntry} + 3:userLength}
password=${infos[2]:${#passwordEntry} + 3:passwordLength}
port=${infos[3]:${#portEntry} + 3:portLength}
database=${infos[4]:${#databaseNameEntry} + 3:databaseNameLength}

sed -i -e "s/<DB_HOST>/${host}/g" $target
sed -i -e "s/<DB_USER>/${user}/g" $target
sed -i -e "s/<DB_PASSWORD>/${password}/g" $target
sed -i -e "s/<DB_PORT>/${port}/g" $target
sed -i -e "s/<DB_NAME>/${database}/g" $target

# APPLICATION
appProfileEntry='user_app_profile'
appLogPathEntry='user_app_logs_path'

appProfileLength=$((${#infos[5]} - ${#appProfileEntry} - 5))
appLogsPathLength=$((${#infos[6]} - ${#appLogPathEntry} - 5))

appProfile=${infos[5]:${#appProfileEntry} + 3:appProfileLength}
appLogsPath=${infos[6]:${#appLogPathEntry} + 3:appLogsPathLength}

sed -i -e "s/<PROFILE>/${appProfile}/g" $target
sed -i -e 's|<LOGS_PATH>|'$appLogsPath'|g' $target

# DOCKER
dockerPortEntry='user_docker_port'
dockerLogPathEntry='user_docker_logs_path'
dockerNetworkEntry='user_docker_network'

dockerPortLength=$((${#infos[7]} - ${#dockerPortEntry} - 5))
dockerLogsPathLength=$((${#infos[8]} - ${#dockerLogPathEntry} - 5))
dockerNetworkLength=$((${#infos[9]} - ${#dockerNetworkEntry} - 5))

dockerPort=${infos[7]:${#dockerPortEntry} + 3:dockerPortLength}
dockerLogsPath=${infos[8]:${#dockerLogPathEntry} + 3:dockerLogsPathLength}
dockerNetwork=${infos[9]:${#dockerNetworkEntry} + 3:dockerNetworkLength}

sed -i -e "s/<APP_PORT>/\"${dockerPort}\"/g" $composeTarget
sed -i -e 's|<LOGS_PATH>|'$dockerLogsPath'|g' $composeTarget
sed -i -e "s/<DOCKER_NETWORK>/${dockerNetwork}/g" $composeTarget

# LAUNCH DOCKER
docker-compose up -d

# DELETE
rm $target-e
rm $composeTarget-e
rm Dockerfile
rm docker-compose.yml
rm user-infos.yml