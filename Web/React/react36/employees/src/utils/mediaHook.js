import {useState, useEffect} from 'react';
const useColumnsMedia = (columnsMediaObject) => {

    const mediaQueryList = Object.keys(columnsMediaObject).map(q =>
        window.matchMedia(q));
    const getColumns = () => {
        const mediaQuery = mediaQueryList.find(mq => mq.matches);
        return mediaQuery ? columnsMediaObject[mediaQuery.media] :
            columnsMediaObject.default;
    }
    const [columns, setColumns] = useState(getColumns());
    useEffect(() => {
        const handler = () => {
            setColumns(getColumns());
        }
        mediaQueryList.forEach(mq => mq.addEventListener('change', handler))
    },[])
    return columns;
}
export  default useColumnsMedia;