$().ready(function(){
	//var map = new AMap.Map('container');
	
	  var map = new AMap.Map('container', {
	        zoom: 4
	    });
	  
	  var opt = {
			  position : (116.404, 39.915),
			  
	  }
	  
	  AMap.Marker(opt);
	
});
