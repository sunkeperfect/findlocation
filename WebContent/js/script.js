function bindCarousel(){
    jQuery('#carousel_point li').click(function(){
        jQuery('#carousel_point li.libg').removeClass('libg')
        jQuery(this).addClass('libg');
        var img_num = parseInt(jQuery(this).attr('turn'));
        carousel(img_num);
    })
}
function carousel(n){
    var fadout_dom = jQuery('#carousel_container .pt:visible');
    fadout_dom.removeClass('pt_cur');
    fadout_dom.fadeOut(1000);
    var fadin_dom = jQuery('#carousel_container .pt').eq(n-1);
    fadin_dom.fadeIn(1000,
	function(){
    	fadin_dom.addClass('pt_cur');
    });
    if(n==1){
        jQuery('#carousel_up').addClass('uN');
        jQuery('#carousel_up').removeAttr('onclick');
        jQuery('#carousel_down').removeClass('dN');
        jQuery('#carousel_down').attr('onclick','carouselNext()');
    }
    else if(n==3){
        jQuery('#carousel_down').addClass('dN');
        jQuery('#carousel_down').removeAttr('onclick');
        jQuery('#carousel_up').removeClass('uN');
        jQuery('#carousel_up').attr('onclick','carouselPrevious()');
    }
    else{
        jQuery('#carousel_up').removeClass('uN');
        jQuery('#carousel_up').attr('onclick','carouselPrevious()');
        jQuery('#carousel_down').removeClass('dN');
        jQuery('#carousel_down').attr('onclick','carouselNext()');
    }
    clearTimeout(carousel_taskId);
    carousel_taskId = setTimeout(carouselNext,4000);
}
function carouselNext(){
    var cur_cursor = jQuery('#carousel_point li.libg').removeClass('libg');
    var cur_img_turn = parseInt(cur_cursor.attr('turn'));
    var next_img_turn;
    if(cur_img_turn==3){
        next_img_turn = 1;
    }else{
        next_img_turn = cur_img_turn + 1;
    }
    var next_cursor = jQuery('#carousel_point li[turn='+next_img_turn+']');
    next_cursor.addClass('libg');
    carousel(next_img_turn);
}
function carouselPrevious(){
    var cur_cursor = jQuery('#carousel_point li.libg').removeClass('libg');
    var cur_img_turn = parseInt(cur_cursor.attr('turn'));
    var previous_img_turn;
    if(cur_img_turn==1){
        previous_img_turn = 3;
    }else{
        previous_img_turn = cur_img_turn - 1;
    }
    var previous_cursor = jQuery('#carousel_point li[turn='+previous_img_turn+']');
    previous_cursor.addClass('libg');
    carousel(previous_img_turn);
}