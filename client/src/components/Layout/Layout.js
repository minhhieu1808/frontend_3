import React from "react";
import Footer from "./Footer";
import Header from "./Header";
import {Helmet}  from "react-helmet";
import {Toaster} from 'react-hot-toast';
import 'react-toastify/dist/ReactToastify.css';
const Layout = ({ children, title, description, keywords, author }) =>{
    return (
        <div>
            <Helmet>
                <meta charSet="UTF-8"/>
                <div>
                    <meta name="description" content={description}/>
                    <meta name="keywords" content={keywords}/>
                    <meta name="author" content={author}></meta>
                </div>
                <title>{title}</title>
            </Helmet>
            <Header/>
                {/* <h1>Layout </h1> */}
                <main style={{minHeight: "70vh"}}>
                    <Toaster/>
                    {children}</main>
            <Footer/>
        </div>
    );
};

Layout.defaultProps = {
    title: "Ecommerce App",
    description: "GR1 project",
    keywords: "react, java springboot",
    author: "HieuNM",
  };

export default Layout;