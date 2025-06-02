// src/FileTable.js
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const FileTable = () => {
  const [files, setFiles] = useState([]);

  const fetchFiles = async () => {
    try {
      const res = await axios.get('http://localhost:8080/api/files');
      setFiles(res.data);
    } catch (error) {
      console.error('Error fetching files:', error);
    }
  };

  useEffect(() => {
    fetchFiles();
  }, []);

  return (
    <div>
      <h2>Files in Database</h2>
      <table border="1">
        <thead>
          <tr>
            <th>File Name</th>
            <th>Content Type</th>
            <th>Size (bytes)</th>
            <th>Upload Time</th>
          </tr>
        </thead>
        <tbody>
          {files.map((file) => (
            <tr key={file.id}>
              <td>{file.fileName}</td>
              <td>{file.contentType}</td>
              <td>{file.fileSize}</td>
              <td>{new Date(file.uploadTime).toLocaleString()}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default FileTable;
