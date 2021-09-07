$(document).ready(function () {
    $('#fileImage').change(function () {
        preViewImage(this);
    });
});

function preViewImage(fileImage) {
    let file = fileImage.files[0];
    let reader = new FileReader();
    reader.onload = function (e) {
        $('#preViewImage').attr('src', e.target.result);
    };
    reader.readAsDataURL(file);
}