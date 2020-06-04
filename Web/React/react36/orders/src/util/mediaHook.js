import { useState, useEffect } from "react";

const useColumnsMedia = (columnsMediaObject) =>{



const mediaQueryList = Object.keys(columnsMediaObject).map(g => window.matchMedia(g));

//
const getColumns = () =>{
const mediaQuery = mediaQueryList.find(mg =>mg.matches);
return mediaQuery ? columnsMediaObject[mediaQuery.media]:columnsMediaObject.default;

}
//
const [columns, setColumns] = useState(getColumns());
useEffect(()=>{
    const handler = () => {
        console.log(getColumns())
        setColumns(getColumns());
    }
    mediaQueryList.forEach(mg=>mg.addEventListener('change',handler))
},[])

return columns;
}
export default useColumnsMedia;