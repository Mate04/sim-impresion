import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Alert from '@mui/material/Alert';
import Table from '../components/Table.jsx'
import service from '../service/service.js'
import Loader from '../components/Loader.jsx'

const Main = () => {
    const [tiempoSimulacion, setTiempoSimulacion] = useState('');
    const [inicioMuestra, setInicioMuestra] = useState('');
    const [cantidadIteraciones, setCantidadIteraciones] = useState('');
    const [limiteInferiorImpresion, setLimiteInferiorImpresion] = useState('');
const [limiteSuperiorImpresion, setLimiteSuperiorImpresion] = useState('');
const [mediaLlegadaAsistentes, setMediaLlegadaAsistentes] = useState('');
const [limiteInferiorMantenimiento, setLimiteInferiorMantenimiento] = useState('');
const [limiteSuperiorMantenimiento, setLimiteSuperiorMantenimiento] = useState('');
const [limiteInferiorTecnico, setLimiteInferiorTecnico] = useState('');
const [limiteSuperiorTecnico, setLimiteSuperiorTecnico] = useState('');

    const [error, setError] = useState('');
    const [data, setData] = useState(null)
    const [loading, setLoading] = useState(false)




    const handleSubmit = async (e) => {
        e.preventDefault();

        const tiempo = parseFloat(tiempoSimulacion);
        const inicio = parseFloat(inicioMuestra);
        const iteraciones = parseInt(cantidadIteraciones, 10);
        

        // Validaciones
        if (isNaN(tiempo) || tiempo <= 0 || isNaN(inicio) || inicio < 0 || isNaN(iteraciones) || iteraciones <= 0) {
            setError('Verificá que todos los campos sean números válidos y positivos.');
            return;
        }

        if (inicio > tiempo) {
            setError('El inicio de la muestra no puede ser mayor al tiempo de simulación.');
            return;
        }
        setError('');
        try {
            setLoading(true)
            
            const info = {
    tiempo: parseFloat(tiempo),
    inicio: parseFloat(inicio),
    iteraciones: parseInt(iteraciones, 10),
    limInfTiempoImpresion: parseFloat(limiteInferiorImpresion),
    limSupTiempoImpresion: parseFloat(limiteSuperiorImpresion),
    limInfTiempoMantenimiento: parseFloat(limiteInferiorMantenimiento),
    limSupTiempoMantenimiento: parseFloat(limiteSuperiorMantenimiento),
    expAsistente: parseFloat(mediaLlegadaAsistentes),
    limInfTiempoFinDescanso: parseFloat(limiteInferiorTecnico),
    limSupTiempoFinDescanso: parseFloat(limiteSuperiorTecnico)
};
            console.log(info);
            
            const data = await service.obtenerIteraciones(info)
            setData(data)

        } catch (error) {
            alert('error al cargar simulacion')
        } finally{
            setLoading(false)
        }
        

        
        
    };

    return (
        <div>
            <div style={{ maxWidth: 600, margin: '2rem auto', padding: 20, border: '5px solid #ccc', borderRadius: 8 }}>
                <h2>Simulación Punto de Impresión</h2>

                {error && <Alert severity="error" style={{ marginBottom: 16 }}>{error}</Alert>}

                <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}>
                    <TextField
                        label="Tiempo de simulación"
                        type="number"
                        variant="standard"
                        value={tiempoSimulacion}
                        onChange={e => setTiempoSimulacion(e.target.value)}
                        required
                        inputProps={{ min: 0, step: "any" }}
                    />
                    <TextField
                        label="Inicio muestra de simulación"
                        type="number"
                        variant="standard"
                        value={inicioMuestra}
                        onChange={e => setInicioMuestra(e.target.value)}
                        required
                        inputProps={{ min: 0, step: "any" }}
                    />
                    <TextField
                        label="Cantidad de iteraciones"
                        type="number"
                        variant="standard"
                        value={cantidadIteraciones}
                        onChange={e => setCantidadIteraciones(e.target.value)}
                        required
                        inputProps={{ min: 1, step: "1" }}
                    />
                    <TextField
    label="Límite inferior tiempo de impresión"
    type="number"
    variant="standard"
    value={limiteInferiorImpresion}
    onChange={e => setLimiteInferiorImpresion(e.target.value)}
    required
    inputProps={{ min: 0, step: "any" }}
/>
<TextField
    label="Límite superior tiempo de impresión"
    type="number"
    variant="standard"
    value={limiteSuperiorImpresion}
    onChange={e => setLimiteSuperiorImpresion(e.target.value)}
    required
    inputProps={{ min: 0, step: "any" }}
/>
<TextField
    label="Media llegada de asistentes"
    type="number"
    variant="standard"
    value={mediaLlegadaAsistentes}
    onChange={e => setMediaLlegadaAsistentes(e.target.value)}
    required
    inputProps={{ min: 0, step: "any" }}
/>
<TextField
    label="Límite inferior tiempo de mantenimiento"
    type="number"
    variant="standard"
    value={limiteInferiorMantenimiento}
    onChange={e => setLimiteInferiorMantenimiento(e.target.value)}
    required
    inputProps={{ min: 0, step: "any" }}
/>
<TextField
    label="Limite superior tiempo de mantenimiento"
    type="number"
    variant="standard"
    value={limiteSuperiorMantenimiento}
    onChange={e => setLimiteSuperiorMantenimiento(e.target.value)}
    required
    inputProps={{ min: 0, step: "any" }}
/>
<TextField
    label="Limite superior tecnico"
    type="number"
    variant="standard"
    value={limiteSuperiorTecnico}
    onChange={e => setLimiteSuperiorTecnico(e.target.value)}
    required
    inputProps={{ min: 0, step: "any" }}
/>
<TextField
    label="Limite inferior tecnico"
    type="number"
    variant="standard"
    value={limiteInferiorTecnico}
    onChange={e => setLimiteInferiorTecnico(e.target.value)}
    required
    inputProps={{ min: 0, step: "any" }}
/>


                    <Button type="submit" variant="contained" color="primary">
                        Iniciar simulación
                    </Button>
                </form>
            </div>
            <div>
                {
                    (data !== null )? (
                        <Table rows={data.simulacion} tiempoPromedioEnCola={data.tiempoPromedioColaAsistente} PorcentajeAsistentesQueSeVan={data.porcentajeAsistentesQueSeVan}></Table>
                    ): null
                }
            </div>
        </div>

    );
};

export default Main;
