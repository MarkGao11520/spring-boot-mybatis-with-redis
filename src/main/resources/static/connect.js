/**
 * Created by gaowenfeng on 2017/7/13.
 */
function connect() {
    $.ajax({
        type: 'post',
        url: './code/connectDb',
        data: {
            ip: $('#ip').val(),
            db: $('#db').val(),
            port: $('#port').val(),
            dbName: $('#dbName').val(),
            username: $('#username').val(),
            password: $('#password').val()
        },
        success: function (result) {
            if(result.code==200){
                alert("连接成功");
                var str = '<br>';
                var list = result.data;
                for (var i = 0; i < list.length; i++)
                    str += (list[i].tableName+':<input type="checkbox" name="table" value="' + list[i].tableName + '">&nbsp;&nbsp;&nbsp;&nbsp;');
                $('#tableDiv').append(str);
            }
        },
        error: function (error) {
            alert(JSON.stringify(error));
        }
    })
}

function generate() {
    var str = "";
    var j = 0
    for (var i = 0; i < document.getElementsByName('table').length; i++) {
        if (document.getElementsByName('table')[i].checked) {
            if (j == 0)
                str += document.getElementsByName('table')[i].value;
            else
                str += "," +document.getElementsByName('table')[i].value;
            j++;
        }
    }
    if (str == "") {
        alert("您没有选择任何数据");
    } else {
        window.location.href = './code/constructCode?ip=' + $('#ip').val() + '&db=' + $('#db').val() + '&port=' + $('#port').val() + '&dbName=' + $('#dbName').val() + '&username=' + $('#username').val() + '&password=' + $('#password').val() + '&tables=' + str + '&basePackage=' + $('#basepackage').val()+'&author='+$('#author').val();
    }
}