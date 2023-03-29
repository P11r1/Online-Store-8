$(document).ready(function(){
    $(".alert-danger, .alert-success").fadeTo(5000, 1000).slideUp(1000, function() {
        $(".alert-success, .alert-danger").slideUp(1000);
    });

    $(document).on('click', '.nav-item a', function() {
        $(this).addClass('active').siblings().removeClass('active');
    });
});