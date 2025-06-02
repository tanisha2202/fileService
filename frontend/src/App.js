// src/App.js
import React, { useRef } from 'react';
import FileUpload from './FileUpload';
import FileTable from './FileTable';

function App() {
  const tableRef = useRef();

  const handleUploadSuccess = () => {
    // Reload the table when upload is successful
    if (tableRef.current) {
      tableRef.current();
    }
  };

  const [fetchTrigger, setFetchTrigger] = React.useState(0);

  return (
    <div className="App">
      <FileUpload onUploadSuccess={() => setFetchTrigger((prev) => prev + 1)} />
      <FileTable key={fetchTrigger} />
    </div>
  );
}

export default App;
