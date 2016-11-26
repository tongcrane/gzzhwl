rm -fr ./ROOT
mkdir ./ROOT
cp ./*.html ./ROOT
cp ./*htm ./ROOT
cp -R ./css/ ./ROOT/css
cp -R ./js/ ./ROOT/js
cp -R ./views/ ./ROOT/views
cp -R ./img/ ./ROOT/img
cp -R ./WEB-INF/ ./ROOT/WEB-INF

if [[ $1 == "test" ]]; then
cp -f ./properties/global_config_test.js ./ROOT/js/public/global_config.js
elif [[ $1 == "dev" ]]; then
cp -f ./properties/global_config_dev.js ./ROOT/js/public/global_config.js
elif [[ $1 == "prd" ]]; then
cp -f ./properties/global_config_prd.js ./ROOT/js/public/global_config.js
elif [[ $1 == "poc" ]]; then
cp -f ./properties/global_config_poc.js ./ROOT/js/public/global_config.js
elif [[ $1 == "local" ]]; then
cp -f ./properties/global_config_local.js ./ROOT/js/public/global_config.js
elif [[ $1 == "tms" ]]; then
cp -f ./properties/global_config_tms.js ./ROOT/js/public/global_config.js
else
cp -f ./properties/global_config_prd.js ./ROOT/js/public/global_config.js
fi
cd ./ROOT

zip -r ../ysj.zip ./*
rm -fr ../ROOT
