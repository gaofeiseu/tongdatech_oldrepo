var myDataRef = new Firebase('https://scorching-fire-8189.firebaseio.com/');

function doadd(){
	var add_key = $("#add_key").val();
	var add_value = $("#add_value").val();
	myDataRef.set('Key ' + add_key + ' Value ' + add_value);
	myDataRef.set({
		  title: "Hello World!",
		  author: "Firebase",
		  location: {
		    city: "San Francisco",
		    state: "California",
		    zip: 94103
		  }
		});
	$("#add_key").val('');
	$("#add_value").val('');
}