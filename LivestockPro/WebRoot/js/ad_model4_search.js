function compare(a, b, column, numeric, order){
  var aValue = a.cells[column].innerHTML;
  var bValue = b.cells[column].innerHTML;
  if (numeric) { aValue = parseFloat(aValue );
                 bValue = parseFloat(bValue ); }
  if (aValue < bValue) return order;
  if (aValue > bValue) return -order;
  return 0;
}
function sortColumn(header, column){
  var rows = $("tbody tr");
  rows.sort(function(a, b){ 
      return compare(a, b, column, header.data("numeric"), header.data("order"));
    });
  $(rows).each(function(){ $("tbody").append($(this)); });
  header.data("order", -header.data("order"));
}
function filterColumn(input, column){
  $("tbody tr").show().each(function(){
    var header = $("th:eq("+ column +")");
    var filterVal = input.val();
    var rowVal = this.cells[column].innerHTML;
    if(header.data("numeric") ){
      if(parseFloat( filterVal ) > parseFloat( rowVal )) { $(this).hide(); }}           
    else {if(rowVal.indexOf(filterVal) < 0) { $(this).hide(); }}          
  });
}
function randInt(max) { return Math.floor((Math.random()*max)+1); }

$(document).ready(function(){
  $("th:lt(6)").each(function(i) {
      var header = $(this);
      header.data({numeric:header.hasClass("numeric"), order:-1});
      header.children("span").click(function(){ sortColumn(header, i); });
      
      var filter = $('<input type="text" style="width:75%;background:'+
        'url(\'../image/search2.png\') no-repeat;background-size:15px 15px;padding-left:15px;" />');
      
      
      filter.keyup(function(){ filterColumn(filter, i); });
      header.append(filter);
    });
});