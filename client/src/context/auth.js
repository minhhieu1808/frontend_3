import { useState, useEffect, useContext, createContext } from "react";
import axios from "axios";
import Password from "antd/es/input/Password";

const AuthContext = createContext();
const AuthProvider = ({ children }) => {
  const [auth, setAuth] = useState({
    user: null,
    token: "",
  });

  //default axios
  axios.defaults.headers.common["Authorization"] = auth?.token;
  console.log(auth);
  useEffect(() => {
    const data = localStorage.getItem("auth");
    console.log("auth data: ",data);
    if (data) {
      const parseData = JSON.parse(data);
      setAuth({
        ...auth,
        user: parseData.data,
        token: parseData.token,
      });

    }
    //eslint-disable-next-line
  }, []);
  return (
    <AuthContext.Provider value={[auth, setAuth]}>
      {children}
    </AuthContext.Provider>
  );
}


const useAuth = () => useContext(AuthContext);

export { useAuth, AuthProvider };