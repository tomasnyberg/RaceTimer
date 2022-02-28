cd program
echo Setting up server 🔧
mkdir -p logs
npm i > logs/log_install 2> logs/error_install

echo Starting server 🏍️
url=`cat ./config.yaml | grep port: | grep -o "[0-9]\+"`
url=$(printf 'http://localhost:%d' $url)
echo 'Open ' $url
python3 -m webbrowser $url

npm start > logs/log_start 2> logs/error_start
