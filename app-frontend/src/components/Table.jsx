import * as React from 'react';
import { 
  Box, 
  Collapse, 
  IconButton, 
  Table, 
  TableBody, 
  TableCell, 
  TableContainer, 
  TableHead, 
  TableRow, 
  Typography, 
  Paper 
} from '@mui/material';
import { KeyboardArrowDown, KeyboardArrowUp } from '@mui/icons-material';


function Row({ row }) {
  const [open, setOpen] = React.useState(false);

  // Helper to round numbers to 2 decimals, but keep non-numbers as is
  const round2 = (val) =>
    typeof val === 'number' ? val.toFixed(2) : val;

  return (
    <>
      <TableRow>
        <TableCell sx={{ borderRight: '1px solid #ccc' }}>
          <IconButton size="small" onClick={() => setOpen(!open)}>
            {open ? <KeyboardArrowUp /> : <KeyboardArrowDown />}
          </IconButton>
        </TableCell>
        <TableCell align="right" sx={{ borderRight: '1px solid #ccc' }}>{row.evento}</TableCell>
        <TableCell align="right" sx={{ borderRight: '1px solid #ccc' }}>{round2(row.reloj)}</TableCell>
        <TableCell align="right">{round2(row.proxAsist.rnd)}</TableCell>
        <TableCell align="right" sx={{ borderRight: '1px solid #ccc' }}>{round2(row.proxAsist.hora)}</TableCell>
        <TableCell align="right">{round2(row.proxTecnico.rnd)}</TableCell>
        <TableCell align="right" sx={{ borderRight: '1px solid #ccc' }}>{round2(row.proxTecnico.hora)}</TableCell>
        <TableCell align="right" sx={{ borderRight: '1px solid #ccc' }}>{row.estadoTecnico}</TableCell>
        <TableCell align="right" sx={{ borderRight: '1px solid #ccc' }}>{(row.colaAsistentes)}</TableCell>

        {row.puntosImpresion.map((punto) => (
          <React.Fragment key={punto.id}>
            <TableCell align="right">{punto.estado}</TableCell>
            <TableCell align="right">{round2(punto.hora)}</TableCell>
            <TableCell align="right" sx={{ borderRight: '1px solid #ccc' }}>{(punto.acFlag ? "REPARADA" : "NO REPARADA")}</TableCell>
          </React.Fragment>
        ))}

        <TableCell align="right" sx={{ borderRight: '1px solid #ccc' }}>{(row.acAsistenteCola)}</TableCell>
        <TableCell align="right" sx={{ borderRight: '1px solid #ccc' }}>{round2(row.acTiempoCola)}</TableCell>
        <TableCell align="right">{round2(row.acAsistPostergados)}</TableCell>
      </TableRow>

      <TableRow>
        <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={100}>
          <Collapse in={open} timeout="auto" unmountOnExit>
            <Box sx={{ margin: 1 }}>
              <Typography variant="h6" gutterBottom>
                Asistentes en el instante de Simulación
              </Typography>
              <Table size="small">
                <TableHead>
                  <TableRow>
                    <TableCell>Id</TableCell>
                    <TableCell>Estado</TableCell>
                    <TableCell>Hora llegada Cola</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {row.asistente.map((asistente) => (
                    <TableRow key={asistente.id}>
                      <TableCell>{asistente.id}</TableCell>
                      <TableCell>{asistente.estado}</TableCell>
                      <TableCell>{round2(asistente.horaLlegadaCola)}</TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </Box>
          </Collapse>
        </TableCell>
      </TableRow>
    </>
  );
}

export default function CollapsibleTable({rows}) {
  return (
    <TableContainer component={Paper} sx={{ maxHeight: 500 }}>
      <Table stickyHeader>
        <TableHead>
          <TableRow sx={{ backgroundColor: '#676767' }}>
            <TableCell sx={{ color: '#fff', position: 'sticky', top: 0, backgroundColor: '#676767' }} rowSpan={2} />
            <TableCell align="right" sx={{ ...headStyle }} rowSpan={2}>Evento</TableCell>
            <TableCell align="right" sx={{ ...headStyle }} rowSpan={2}>Reloj</TableCell>
            <TableCell align="center" colSpan={2} sx={{ ...headStyle }}>Próx Asist</TableCell>
            <TableCell align="center" colSpan={2} sx={{ ...headStyle }}>Próx Técnico</TableCell>
            <TableCell align="right" sx={{ ...headStyle }} rowSpan={2}>Estado Técnico</TableCell>
            <TableCell align="right" sx={{ ...headStyle }} rowSpan={2}>Cola Asistentes</TableCell>
            {rows[0].puntosImpresion.map(p => (
              <TableCell key={p.id} align="center" colSpan={3} sx={{ ...headStyle }}>
                Punto de Impresión {p.id}
              </TableCell>
            ))}
            <TableCell align="right" sx={{ ...headStyle }} rowSpan={2}>AC Asist Cola</TableCell>
            <TableCell align="right" sx={{ ...headStyle }} rowSpan={2}>AC Tiempo Cola</TableCell>
            <TableCell align="right" sx={{ ...headStyle }} rowSpan={2}>AC Postergados</TableCell>
          </TableRow>
          <TableRow sx={{ backgroundColor: '#929292' }}>
            <TableCell align="right" sx={subHeadStyle}>RND</TableCell>
            <TableCell align="right" sx={subHeadStyle}>Hora</TableCell>
            <TableCell align="right" sx={subHeadStyle}>RND</TableCell>
            <TableCell align="right" sx={subHeadStyle}>Hora</TableCell>
            {rows[0].puntosImpresion.map((_, i) => (
              <React.Fragment key={i}>
                <TableCell align="right" sx={subHeadStyle}>Estado</TableCell>
                <TableCell align="right" sx={subHeadStyle}>Hora</TableCell>
                <TableCell align="right" sx={subHeadStyle}>AC/Flag Mant</TableCell>
              </React.Fragment>
            ))}
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <Row key={row.reloj} row={row} />
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}

const headStyle = {
  borderRight: '1px solid #ccc',
  color: '#fff',
  fontWeight: 'bold',
  position: 'sticky',
  top: 0,
  backgroundColor: '#676767',
  zIndex: 2,
};

const subHeadStyle = {
  color: '#fff',
  fontWeight: 'bold',
  position: 'sticky',
  top: 56, // altura ajustada para una sola fila de encabezado
  backgroundColor: '#929292',
  zIndex: 1,
  borderRight: '1px solid #ccc',
}