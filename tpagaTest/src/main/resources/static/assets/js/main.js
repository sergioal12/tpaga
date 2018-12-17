/*
	Stellar by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
*/

(function($) {

	var	$window = $(window),
		$body = $('body'),
		$main = $('#main');

	// Breakpoints.
		breakpoints({
			xlarge:   [ '1281px',  '1680px' ],
			large:    [ '981px',   '1280px' ],
			medium:   [ '737px',   '980px'  ],
			small:    [ '481px',   '736px'  ],
			xsmall:   [ '361px',   '480px'  ],
			xxsmall:  [ null,      '360px'  ]
		});

	// Play initial animations on page load.
		$window.on('load', function() {
			window.setTimeout(function() {
				$body.removeClass('is-preload');
			}, 100);
		});

	// Nav.
		var $nav = $('#nav');

		if ($nav.length > 0) {

			// Shrink effect.
				$main
					.scrollex({
						mode: 'top',
						enter: function() {
							$nav.addClass('alt');
						},
						leave: function() {
							$nav.removeClass('alt');
						},
					});

			// Links.
				var $nav_a = $nav.find('a');

				$nav_a
					.scrolly({
						speed: 1000,
						offset: function() { return $nav.height(); }
					})
					.on('click', function() {

						var $this = $(this);

						// External link? Bail.
							if ($this.attr('href').charAt(0) != '#')
								return;

						// Deactivate all links.
							$nav_a
								.removeClass('active')
								.removeClass('active-locked');

						// Activate link *and* lock it (so Scrollex doesn't try to activate other links as we're scrolling to this one's section).
							$this
								.addClass('active')
								.addClass('active-locked');

					})
					.each(function() {

						var	$this = $(this),
							id = $this.attr('href'),
							$section = $(id);

						// No section for this link? Bail.
							if ($section.length < 1)
								return;

						// Scrollex.
							$section.scrollex({
								mode: 'middle',
								initialize: function() {

									// Deactivate section.
										if (browser.canUse('transition'))
											$section.addClass('inactive');

								},
								enter: function() {

									// Activate section.
										$section.removeClass('inactive');

									// No locked links? Deactivate all links and activate this section's one.
										if ($nav_a.filter('.active-locked').length == 0) {

											$nav_a.removeClass('active');
											$this.addClass('active');

										}

									// Otherwise, if this section's link is the one that's locked, unlock it.
										else if ($this.hasClass('active-locked'))
											$this.removeClass('active-locked');

								}
							});

					});

		}

	// Scrolly.
		$('.scrolly').scrolly({
			speed: 1000
		});
		
		 var valueElement = $('#value');
		    function incrementValue(e){
		        valueElement.text(Math.max(parseInt(valueElement.text()) + e.data.increment, 0));
		        return false;
		    }

		    $('#plus').bind('click', {increment: 1}, incrementValue);

		    $('#minus').bind('click', {increment: -1}, incrementValue);
		    var elmentNum = parseInt(valueElement);
		    
		    
		    var tokenTpaga;
		    $('#comprar').on('click touchstart', function(){
		    	
		    	var numPedido = parseInt($('#value').text()) * 1230;
		    	
		    	$.ajax({
		    		url:"https://192.168.0.7:9443/tienda/crearPago",
		    		type: "POST",
		    		data: {numPedido},
		    		success:function(response){
		    			
		    			document.cookie = response.token; 
		    			tokenTpaga = response.token;
		    			alert(response);
		    			//console.log(response.status)
		    			window.location = response.tpaga_payment_url;
		    			
		    		},
		    		error: function(error){
		    			alert("esto es error "+ error.toString());
		    		}
		    		
		    	});
		    });
		    
		    if(window.location.href=="https://192.168.0.7:9443/generic.html"){
		    	//console.log("aca deberia lanzar el otro ajax: " + document.cookie);
		    	//alert("listo para lanzar el otro ajax: "+document.cookie);
		    	var token = document.cookie;
		    	alert(token);
		    	function afterLoad(){
		    		alert("dentro de after load");
		    		$.ajax({
			    		url:"https://192.168.0.7:9443/tienda/confirmarPago",
			    		type: "POST",
			    		data: {token},
			    		success:function(response){
			    			console.log(response.status);
			    			alert("listo para lanzar el otro ajax: "+document.cookie);
			    			//window.location = response;
			    			
			    		},
			    		error: function(error){
			    			console.log("esta vaina fallo ", error);
			    			alert("esto fallo"+error.toString());
			    		}
			    		
			    	});
		    	}
		    	$(document).ready(afterLoad);
		    	
		    }

})(jQuery);