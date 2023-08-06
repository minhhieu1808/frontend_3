import React from "react";
import Layout from "./../components/Layout/Layout";
import logo from "../assets/logo-dai-hoc-bach-khoa.png"
const About = () => {
  return (
    <Layout title={"About us - Ecommer app"}>
      <div className="row contactus ">
        <div className="col-md-6 ">
          <img
            src={logo}
            alt="contactus"
            style={{ width: "100%" }}
          />
        </div>
        <div className="col-md-4">
          <p className="text-justify mt-2">
            Nguyen Minh Hieu Graduation Reasearch I Project
          </p>
        </div>
      </div>
    </Layout>
  );
};

export default About;