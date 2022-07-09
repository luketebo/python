function create(id, title, content, classes, time, deadline) {
    his_snip = ['<div class="card mb-3" style="min-width:37vw"><div class="card-header">',
        '</div><div class="card-body container"><h5 class="card-title">',
        '</h5><p class="card-text">',
        '</p><div class="d-flex justify-content-between"><footer><cite title="Source Title">',
        '&nbsp-&nbsp',
        '</cite></footer><div class="d-flex"><a href="javascript:void(0);"\
        class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#InfoModal" data-bs-action="modify_','">修改</a>&nbsp<a href="javascript:void(0);" onclick="delete_as(',
        ')" class="btn btn-danger">删除</a>&nbsp<a href="javascript:void(0);" onclick="check_as(',
        ')" class="btn btn-success">批改</a></div></div></div></div>']
    return his_snip[0] + title + his_snip[1] + content + his_snip[2] + classes + his_snip[3] + time + his_snip[4] + deadline + his_snip[5] + id + his_snip[6] + id + his_snip[7] + id + his_snip[8];
}

function formatDate(time){
	var date = new Date(time);

	var year = date.getFullYear(),
		month = date.getMonth() + 1,//月份是从0开始的
		day = date.getDate(),
		hour = date.getHours(),
		min = date.getMinutes(),
		sec = date.getSeconds();
	var newTime = year + '-' +
				month + '-' +
				day + ' ' +
				hour + ':' +
				min + ':' +
				sec;
	return newTime;			
}

function createAs() {
    console.log("布置作业");
    $("#main").load("createAs");
}

function checkAs() {
    console.log("");
    $("#main").load("checkAs");
}

function readAs() {
    console.log("批改作业");
    $("#main").load("readAs");
}

function createCl() {
    console.log("创建班级");
    $("#main").load("createCl");
}

function modify_as(id) {
    console.log("修改作业 id: " + id);
}

function delete_as(id) {
    console.log("删除作业 id: " + id);
}

function check_as(id) {
    console.log("批改作业 id: " + id);
}



$(() => {
    cas = document.getElementById("createAs");
    ces = document.getElementById("checkAs");
    ras = document.getElementById("readAs");
    ccl = document.getElementById("createCl");
    cas.addEventListener("click", createAs);
    ces.addEventListener("click", checkAs);
    ras.addEventListener("click", readAs);
    ccl.addEventListener("click", createCl);
});


