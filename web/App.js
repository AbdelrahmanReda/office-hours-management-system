function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}
function showSearchBar() {
    document.getElementById("searchBar").innerHTML = '<form action = "staffCourse.jsp" class="example" style="margin:auto;max-width:300px"> <input class= "example" type="text" placeholder="Search.." name="search" value=""> <button "example" type="submit" ><i class="fa fa-search"></i></button> </form>';
}

function changeValue(appoint,ii)
{

    alert(appoint+" "+ii);
    document.getElementById("appoint").value = (appoint);
    //document.getElementById("staffName").value = staffName;
}


function validateForm() {
                var email = document.forms["myForm"]["email"].value;
                var password = document.forms["myForm"]["password"].value;
                if (email == "") {
                    alert("E-mail must be filled out");
                    return false;
                } else if (password == "") {
                    alert("Password must be filled out");
                    return false;
                }
}

