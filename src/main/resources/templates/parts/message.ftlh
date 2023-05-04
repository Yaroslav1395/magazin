<#macro page>
    <div class="message-pop-up" id="message-pop-up">
        <div class="message-content">
            <div class="close" id="close-message"><i onClick="closeMessage()" class="fa-solid fa-xmark"></i></div>
            <#nested>
        </div>
    </div>
    <script>
        function closeMessage(){
             var messagePopUp = document.getElementById("message-pop-up");
             messagePopUp.style.display = "none";
        };
    </script>
</#macro>