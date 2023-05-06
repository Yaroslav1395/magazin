<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Electronic shop</title>
        <link rel="stylesheet" href="/css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    </head>
    <body>
            <div class="form">
                <@sf.form action="http://localhost:8080/quantum/main/subscribe" method="post" id="subscribe-form">
                    <@sf.input type="email" path="email" placeholder="Your email address"/>
                    <@sf.errors path="email"/>
                    <button class="normal" type="submit">Subscribe</button>
                </@sf.form>
            </div>
    <body>
</html>