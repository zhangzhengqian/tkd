var fileuploada = function() {
    var fileupload_arow = $(".b-fileupload-row");
    if (fileupload_arow.length > 0) {

        var fileupload_html = '<div data-provides="fileupload" class="fileupload fileupload-new">' +
                                    '<div class="input-append">' +
                                        '<div class="uneditable-input span2"> <i class="icon-file fileupload-exists"></i>' +
                                            '<span class="fileupload-preview"></span>' +
                                        '</div>' +
                                        '<span class="btn btn-file">' +
                                            '<span class="fileupload-new">选择文件</span>' +
                                            '<span class="fileupload-exists">重新选择</span>' +

                                            '<input type="file"  name="file" class="default"></span>' +
                                        '<a data-dismiss="fileupload" class="btn fileupload-exists" href="#">清除</a>' +
                                    '</div> ' +
                                '</div>';

        var romove_btn_html = '<a href="#" class="js-remove-upfile"><i class="icon-remove"></i> <span>删除</span></a>';

        $(".js-btn-addup").on('click', function() {
            var filerow = $(this).parent().prev();
            filerow.append(fileupload_html).find(".fileupload:last-child").append(romove_btn_html).find(".js-remove-upfile").on('click' ,function() {$(this).parent().remove();});
            return false;           
        });
    };


}();