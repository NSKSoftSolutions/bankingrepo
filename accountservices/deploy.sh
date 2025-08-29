K8S_YAML_PATH="k8s/account-deployment.yml"
SERVICE_NAME="account-service"
echo "Starting Minikube"
minikube start
echo "Deploying Account-service APP..."
kubectl apply -f $K8S_YAML_PATH
echo "starting the services"
minikube service $SERVICE_NAME