/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validateForm()
{
    //get the 'divError' = display error validations
    var error = document.getElementById('divError');
    
    //get the input of these fields
    var fullName = document.getElementById('tbfullName');
    var fullNameValue = fullName.value;
    var fullNameSplitted = fullNameValue.split(" ");
    var email = document.getElementById('tbemail');
    var DoB = document.getElementById('tbDateofBirth');
    var password = document.getElementById('tbpassword');
    var confirmPassword = document.getElementById('tbconfirmPassword');
    var pass1 = password.value;
    var confPass1 = confirmPassword.value;
    
    //check the whole inputs for empty input or single quote
    var form = document.getElementById("formId");
    var inputs = form.getElementsByTagName("input");
    var input = null;
    for(var i = 0, len = inputs.length; i < len; i++) 
    {
        input = inputs[i];
        if(input.value == "")
	{
            error.innerHTML = 'You cannot leave any of the input blank';
            error.className = 'isVisible';
            input.className = 'errored';
            return false;
	}
        
        if(input.value.indexOf("'") != -1) 
	{
            error.innerHTML = 'input has invalid characters';
            error.className = 'isVisible';
			
            input.className = 'errored';
            return false;
        }
    }
    
    //full name validation
    if(fullNameSplitted.length < 2)
    {
        error.innerHTML = 'Full name is not valid';
        error.className = 'isVisible';
        
        var fnError = document.getElementById('tbfullName_error');
        fnError.className = 'isVisible';   
        fullName.className = 'errored';
	return false;
    }
	
    //regex for password validation
    var regexPass = new RegExp("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])");
    if (!regexPass.test(pass1) && !regexPass.test(confPass1))
    {
	error.innerHTML = 'password must contain a small letter, a capital letter, and a number';
        error.className = 'isVisible';
		
	password.className = 'errored';
        confirmPassword.className = 'errored';
        
	return false;
    }
	
    //check if the password and confirm password match
    if (password.value !== confirmPassword.value)
    {
        error.innerHTML = 'Error! password and confirm password do not match!';
        error.className = 'isVisible';
        input.className = 'isVisible';
        
        var passError = document.getElementById('tbpassword_error');
        var confPassError = document.getElementById('tbconfirmPassword_error');
        
        passError.className = 'isVisible';
        confPassError.className = 'isVisible';
        password.className = 'errored';
        confirmPassword.className = 'errored';
		
	return false;
    }	
    
    input.className = 'TextCSS';
    return true;
}

function DisplayTextBox(){
    if(document.getElementById('securityQuestion').selectedIndex==0)
        document.getElementById('tbsecurityQuestion').className = 'notVisible';
    else
        document.getElementById('tbsecurityQuestion').className = '.VisibleBox';
}
