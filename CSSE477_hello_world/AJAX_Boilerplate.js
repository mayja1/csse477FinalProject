function login_function(){
    getRequest(
        'http://localhost:8080/manage',
        success,
        failure,
        "GET"
    );
    return false;
}
function view_file(){
    getRequest(
        'http://localhost:8080/' + document.getElementById("file").value,
        function (str) {
            document.getElementById("loaded_file").value = str;
        },
        failure,
        "GET"
    )
    return false;
}
function delete_file() {
    getRequest(
        'http://localhost:8080/' + document.getElementById("file").value,
        function(str) {
            document.getElementById("loaded_file").value = str;
        },
        failure,
        "DELETE"
    )
}
function post_file() {
    getRequest(
        'http://localhost:8080/' + document.getElementById("file").value,
        function(str) {
            document.getElementById("loaded_file").value = str;
        },
        failure,
        "POST",
        document.getElementById("loaded_file").value
    )
}
function put_file() {
    getRequest(
        'http://localhost:8080/' + document.getElementById("file").value,
        function(str) {
            document.getElementById("loaded_file").value = str;
        },
        failure,
        document.getElementById("loaded_file").value
    )

}
function failure(str){
    document.getElementById("result").innerHTML = "The server is either down or not operating";
    document.getElementById("main").innerHTML = str;
}
function success(str){
    document.getElementById("result").innerHTML = str;
}
function getRequest(url, success, error, request, content) { //From: http://stackoverflow.com/questions/7165395/call-php-function-from-javascript essentially a well formatted wrapper
    var req = false;
    try{
        // most browsers
        req = new XMLHttpRequest();
    } catch (e){
        // IE
        try{
            req = new ActiveXObject("Msxml2.XMLHTTP");
        } catch(e) {
            // try an older version
            try{
                req = new ActiveXObject("Microsoft.XMLHTTP");
            } catch(e) {
                return false;
            }
        }
    }
    if (typeof content != 'string') content = null;
    if (!req) return false;
    if (typeof success != 'function') success = function () {};
    if (typeof error!= 'function') error = function () {};
    req.onreadystatechange = function(){
        if(req.readyState == 4) {
            return req.status === 200 || req.status === 201 ?
                success(req.responseText) : error(req.status);
        }
    }
    req.open(request, url, true);
    req.send(content);
    return req;
}