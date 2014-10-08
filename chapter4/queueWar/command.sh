for ((i=1;i<=10000;i++))
do   
	curl -v --header "Connection: keep-alive" "http://localhost:8080/queue/enterprise/customers/find?phone=503-555-7555" 
done 
