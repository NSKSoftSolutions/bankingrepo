K8S_YAML_PATH="k8s/customer-deployment.yml"
SERVICE_NAME="customer-service"
echo "Starting Minikube"
minikube start
echo "Deploying Customer-service APP..."
kubectl apply -f $K8S_YAML_PATH
echo "starting the services"
minikube service $SERVICE_NAME

