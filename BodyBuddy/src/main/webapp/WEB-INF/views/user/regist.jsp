<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@include file="./inc/header_link.jsp" %>

<body class="animsition">

	<!-- ê²ì  ë¼ë²¨ì top-bar -->
	<%@include file="./inc/topbar.jsp" %>
	
    <div class="page-header">
        <div class="header">
            <!-- navigation -->
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-sm-6 col-xs-6">
                        <div class="logo">
                            <a href="index.html"><img src="/resources/user/images/logo.png" alt=""></a>
                        </div>
                    </div>
                    <div class="col-md-9 col-sm-12">
                        <div class="navigation pull-right" id="navigation">
                            <ul>
                                <li class="active"><a href="index.html" title="Home" class="animsition-link">Home</a></li>
                                <li><a href="classes-list.html" title="Classes" class="animsition-link">Classes List</a>
                                    <ul>
                                        <li><a href="classes-list.html" title="Classes List">classes List</a></li>
                                        <li><a href="classes-detail.html" title="Classes Detail">classes Detail</a></li>
                                    </ul>
                                </li>
                                <li><a href="blog-default.html" title="Blog" class="animsition-link">Blog Default</a>
                                    <ul>
                                        <li><a href="blog-default.html" title="Blog" class="animsition-link">Blog Default</a></li>
                                        <li><a href="blog-single.html" title="Blog Single" class="animsition-link">Blog Single</a></li>
                                    </ul>
                                </li>
                                <li><a href="testimonial.html" title="Features" class="animsition-link">Features</a>
                                    <ul>
                                        <li><a href="testimonial.html" title="Testimonial" class="animsition-link">Testimonial</a></li>
                                        <li><a href="pricing.html" title="Pricing" class="animsition-link">Pricing</a></li>
                                    </ul>
                                </li>
                                <li><a href="contact.html" title="Contact Us" class="animsition-link">Contact</a> </li>
                                <li><a href="style-guide.html" title="Style Guide" class="animsition-link">style guide</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <div class="page-caption pinside40">
                        <h1 class="page-title">회원가입</h1>
                        <p>멤버가 되어 다양한 혜택을 누려보세요</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <div>
    테스트
    테스트
    테스트
    </div>

    <!-- black footer_space -->
    <%@include file="./inc/footer_space.jsp" %>
    
    <!-- tiny footer -->
    <%@include file="./inc/footer_tiny.jsp" %>
    
	<%@include file="./inc/footer_link.jsp" %>

</body>

</html>
