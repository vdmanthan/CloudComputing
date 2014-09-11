var BASE_URL = "http://localhost:9090/plugin/";

onload = function() {
    	var url = document.URL;
    	getButton();
};

function reportClick(){
	try{
		var request = new XMLHttpRequest();
    		request.open("POST", BASE_URL + 'click', false);
    		request.onload = function() {
        		if (request.status === 201) {
            			document.getElementById("status").innerHTML = "Transmitted Like!";
        			} else {
            			document.getElementById("status").innerHTML = "Unable to transmit Like!";
            			alert('Error '+request.reponseText);
        			}
    			};
    		request.setRequestHeader("Content-Type", 'text/plain');
    		request.send(document.URL);
    }catch(err){
    			alert(err);
            document.getElementById("status").innerHTML += "\nXMLHttprequest error: " + err.description;
    }
}

function getButton(){
	try{
		var request = new XMLHttpRequest();
    		request.open("GET", BASE_URL + 'button', false);
    		request.onload = function() {
        		if (request.status === 200) {
            			document.getElementById("597-like").innerHTML = request.responseText;
            			document.getElementById("status").innerHTML = "Load the like button";
        			} else {
            			document.getElementById("status").innerHTML = "Unable to load the button";
            			alert('Error');
        			}
    			};
    		request.send();
    }catch(err){
    			alert(err);
            document.getElementById("status").innerHTML += "\nXMLHttprequest error: " + err.description;
    }
}
