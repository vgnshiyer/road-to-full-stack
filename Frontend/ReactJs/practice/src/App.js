/* eslint-disable no-unused-vars */
import './App.css';
import {useEffect, useState} from 'react'
import axios from 'axios';

const fetchRandomData = async (url, page=0) => {
  return axios.get(`${url}?page=${page}`)
  .then((res) => {
    return res;
  })
  .catch((err) => {
    return err;
  })
}; 

function App() {
  const [counter, setCounter] = useState(0);
  const [result, setResult] = useState('');
  const [userInfos, setUserInfos] = useState([]);

  const url = 'https://randomuser.me/api';
  // ?page=2

  const fetchData = async (pageNumber = null) => {
    fetchRandomData(url, pageNumber)
    .then((data) => {
      setResult(JSON.stringify(data, null, 2) || 'No user found');
      if (data === null || data === undefined) return;
      setUserInfos([...userInfos, ...data.data.results]);
      console.log(userInfos);
    })
    .catch((err) => {
      console.error(err);
    })
  }

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <>
      <p>
        Page: {counter}
      </p>
      {/* <pre>{result}</pre> */}
      {userInfos.map((userInfo, idx) => (
        <div key={idx}>
          <p>{`${userInfo.name.first}  ${userInfo.name.last}`}</p>
          <img src={userInfo.picture.medium} alt='userimg'/>
        </div>
      ))}
      {/* <button onClick={() => setCounter(counter + 1)}>Click me!</button> */}
      <button onClick={() => {
        setCounter(counter + 1);
        fetchData(counter)
      }}>
        Add more users.
      </button>
    </>
  );
}

export default App;
