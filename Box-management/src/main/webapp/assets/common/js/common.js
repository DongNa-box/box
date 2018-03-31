/**
 * 获得表单参数，以JSON格式返回
 */
(function ($, window, document, undefined) {
    $.fn.serializeJSON = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };

    /**
     * 表单数据回填
     */
    
    var pluginName = 'form';

    $.fn[pluginName] = function (options) {
        if (this == null)
            return null;
        return new BaseForm(this, $.extend(true, {}, options));
    };

    var BaseForm = function (element, options) {
        this.$element = $(element);
        this.options = options;
        //icheck
        this.icheckElement = "[data-flag='icheck']";
        //datepicker
        this.datepickerElement = "[data-flag='datepicker']";
        //datepicker begin today
        this.todayElement = "[data-flag='today']";
        
        this.init();
    }
    
    //初始化
    BaseForm.prototype.init = function () {
        // datepicker
        this.initDatePicker();
        // datepicker begin today
        this.initDatePickerBeginToday();
    }
    
    
    /**
     * 初始化icheck
     */
    BaseForm.prototype.initICheck = function () {
        var form = this.$element;
        if (form.find('[data-flag="icheck"]').length > 0) {
            form.find('[data-flag="icheck"]').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green'
            }).on('ifChanged', function (e) {
                var field = $(this).attr('name');
                form.bootstrapValidator('updateStatus', field, 'NOT_VALIDATED')
                    .bootstrapValidator('validateField', field);
            });
        }
    }

    /**
     * 初始化datepicker
     */
    BaseForm.prototype.initDatePicker = function () {
        var form = this.$element;
        if (form.find('[data-flag="datepicker"]').length > 0) {
            form.find('[data-flag="datepicker"]').datepicker({
                autoclose: true,
                //todayBtn: true,
                format: $(this).attr("format") ? $(this).attr("format") : "yyyy-mm-dd",
                //startDate:new Date(),
                language: 'zh-CN'
            }).on('change', function (e) {
                var field = $(this).attr('name');
                form.data('bootstrapValidator')
                    .updateStatus(field, 'NOT_VALIDATED', null)
                    .validateField(field);
            }).parent().css("padding-left", "15px").css("padding-right", "15px");
        }
    }
    
    BaseForm.prototype.initDatePickerBeginToday = function () {
        var form = this.$element;
        if (form.find('[data-flag="today"]').length > 0) {
            form.find('[data-flag="today"]').datepicker({
                autoclose: true,
                //todayBtn: true,
                format: $(this).attr("format") ? $(this).attr("format") : "yyyy-mm-dd",
                startDate:new Date(),
                language: 'zh-CN'
            }).on('change', function (e) {
                var field = $(this).attr('name');
                form.data('bootstrapValidator')
                    .updateStatus(field, 'NOT_VALIDATED', null)
                    .validateField(field);
            }).parent().css("padding-left", "15px").css("padding-right", "15px");
        }
    }    
    
    
    
    BaseForm.prototype.initFormData = function (json_data) {
        if (!json_data)
            return;
        var form = this.$element;
        if (form.length == 0)
            return;
        form.find('input[name], select[name], textarea[name], label[name]').each(function (ind, elem) {
        	var obj = $(elem), el_name = obj.attr('name'), value;
            try {
                value = eval('json_data.' + el_name);
            } catch (e) {
                value = null;
            }
            if (value != undefined && value != null && $.trim(value) != '') {
                var is_radio = elem.type == 'radio', is_ckbox = elem.type == 'checkbox';
                var is_date = $(elem).data("flag") == "datepicker" || $(elem).data("flag") == "today";
                var date_format = $(elem).data("format") || "yyyy-MM-dd";
                if (is_date)
                    value = formatDate(value, date_format);
                if (is_radio) {
                    //icheck
                    if ($(elem).data("flag") == "icheck") {
                        $(elem).iCheck(elem.value == value ? 'check' : 'uncheck');
                        //form.data('bootstrapValidator').updateStatus(el_name, 'NOT_VALIDATED', null);
                    } else {
                        //原生radio
                        elem.checked = elem.value == value;
                    }
                } else if (is_ckbox) {
                    //icheck
                    if ($(elem).data("flag") == "icheck") {
                        $(elem).iCheck($.inArray(elem.value, value.split(',')) > -1 ? 'check' : 'uncheck');
                        form.data('bootstrapValidator').updateStatus(el_name, 'NOT_VALIDATED', null);
                    } else {
                        //原生checkbox
                        elem.checked = $.inArray(elem.value, value.split(',')) > -1 ? true : false;
                    }
                } else if (elem.tagName.toUpperCase() == 'LABEL') {
                    elem.innerText = value;
                }else if(elem.tagName.toUpperCase()=='SELECT'){
                    $(elem).val(value);
                } else {
                    elem.value = value;
                }
            }
        });
    }
    
    
    /**
     * 表单重置
     */
    BaseForm.prototype.clearForm = function () {
        if (this.$element.length > 0) {
            var form = this.$element;
            form.find(':input[name]:not(:radio)').val('');
            form.find(':input').removeAttr("readonly");
            form.find(':input').removeAttr("disabled");
            form.find(':radio').attr('checked', false);
            form.find(':radio[data-flag]').iCheck('update');
            form.find(':checkbox').attr('checked', false);
            form.find(':checkbox[data-flag]').iCheck('update');
            form.find('label[name]').text('');
            form.find('select:not(.select2)').val("");
            if (form.data('bootstrapValidator'))
                form.data('bootstrapValidator').resetForm();
        } else {
            $(':input[name]:not(:radio)').val('');
            $(':input').removeAttr("readonly");
            $(':input').removeAttr("disabled");
            $(':radio').removeAttr('checked');
            $(':radio[data-flag]').iCheck('update');
            $(':checkbox').removeAttr('checked');
            $(':checkbox[data-flag]').iCheck('update');
            $('label[name]').text('');
            $('select:not(.select2)').val("");
        }
    }
    
    
})(jQuery, window, document);



