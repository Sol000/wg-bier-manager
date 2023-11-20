import argparse
import os

parser=argparse.ArgumentParser()
parser.add_argument('-p', '--prod', action='store_const', const=1, help='starting the server in prod mode')
parser.add_argument('-l', '--local', action='store_const', const=1, help='starting the server in local mode(without ssl)')
parser.add_argument('-s', '--setup', action='store_const', const=1, help='sets up the environment. Installs dependencies')
parser.add_argument('--hard-reset', action='store_const', const=1, help='deletes the database backup')
args=parser.parse_args()

def setup_env():
    os.system('for pkg in docker.io docker-doc docker-compose docker-compose-v2 podman-docker containerd runc; do sudo apt-get remove $pkg; done')
    os.system('sudo apt-get install ca-certificates curl gnupg')
    os.system('sudo install -m 0755 -d /etc/apt/keyrings')
    os.system('curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg')
    os.system('sudo chmod a+r /etc/apt/keyrings/docker.gpg')
    os.system('''echo \
      "deb [arch="$(dpkg --print-architecture)" signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
      "$(. /etc/os-release && echo "$VERSION_CODENAME")" stable" | \
      sudo tee /etc/apt/sources.list.d/docker.list > /dev/null''')
    os.system('sudo apt-get update')
    os.system('sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin')
    os.system('sudo apt install unzip zip')
    os.system('curl -s "https://get.sdkman.io" | bash')
    os.system('source "$HOME/.sdkman/bin/sdkman-init.sh"')
    os.system('sdk install java 21.0.1-tem')
    os.system('sdk install gradle 8.4')
    os.system('source ~/.bashrc')

if __name__ == '__main__':
  if args._get_kwargs()[0][1] and args._get_kwargs()[1][1]:
     print("[ArgError] can't run PROD and LOCAL mode at the same time!")
     exit(-1)
  
  if args._get_kwargs()[2][1]:
     print("setup...")
     setup_env()

  if args._get_kwargs()[3][1]:
     print("hard reset...")
     os.system("sudo docker volume rm wg-bier-manager_mongodb_data_container")

  if args._get_kwargs()[0][1]:
     print("prod mode...")
     os.system("sudo docker compose --profile prod up --force-recreate --build -d")
     
  if args._get_kwargs()[1][1]:
     print("local mode...")
     os.system("sudo docker compose --profile local up --force-recreate --build -d")


  