import axios from 'axios';

const url = 'http://localhost:8080/sim';

const rows = [
  {
    evento: 'Llegada Asistente',
    reloj: '10:05',
    proxAsist: { hora: '10:10', RND: 0.45 },
    proxTecnico: { hora: '10:14', RND: 0.65 },
    proxFinImpresion: '10:20',
    estadoTecnico: 'Ocupado',
    colaAsistentes: 2,
    puntosImpresion: [
      { id: 1, estado: 'Libre', hora: '10:03', acFlag: '' },
      { id: 2, estado: 'Libre', hora: '10:03', acFlag: '' },
      { id: 3, estado: 'Libre', hora: '10:03', acFlag: '' },
      { id: 4, estado: 'Libre', hora: '10:03', acFlag: '' },
      { id: 5, estado: 'Libre', hora: '10:03', acFlag: '' },
      { id: 6, estado: 'Libre', hora: '10:03', acFlag: '' },
    ],
    acAsistenteCola: 3,
    acTiempoCola: 15,
    acAsistPostergados: 1,
    asistente: [
      { id: 1, estado: 'Esperando', horaLlegadaCola: '10:03' },
      { id: 2, estado: 'Esperando', horaLlegadaCola: '10:04' },
    ],
  },
];


const obtenerIteraciones = async (data) => {
    try {
        console.log(data)
        const response = await axios.post(url, data);
        return response.data;
        //return rows;
    } catch (error) {
        console.error('Error in POST request:', error);
        throw error;
    }
};

export default {obtenerIteraciones}