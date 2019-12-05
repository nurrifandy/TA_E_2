$(document).ready(function () {
  AOS.init({
    easing: 'ease',
    duration: 1800,
    once: true,
  });
  
  $('a[href*="#"]').on('click', function () {
    $('html, body').animate({
      scrollTop: $($(this).attr('href')).offset().top - 0
    }, 1000);
  });
  
  $(".dropdown-toggle").dropdown();
  
  $('#up').on('click', function () {
    $('html, body').animate({
      scrollTop: 0
    }, 1000)
  });
  
  $("#pesan").modal('show');
			
})
