package Hospital.Dao;

import Hospital.Conexion.CConexion;
import Hospital.Modelo.CDoctor;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Date;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
/**
 * @author Wilson
 */
public class DoctorDAO {
    
    public  ArrayList<CDoctor> Listar_Doctores(){
        ArrayList<CDoctor> list = new ArrayList<CDoctor>();
        CConexion objCon = new CConexion();
        String sql = "SELECT Id_Doctor, Nombre, Apellido, Especialidad, Dni, Telefono, Id_Usuario,Especialidad,Foto FROM Doctores;";
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            // Preparar la consulta
            ps = objCon.EstablecerConexion().prepareStatement(sql);
            rs = ps.executeQuery();

            // Recorrer los resultados
            while (rs.next()) {
                CDoctor doc = new CDoctor(
                rs.getInt("Id_Doctor"),                        // ID del doctor
                rs.getString("Nombre"),                        // Nombre
                rs.getDate("Fecha_Nacimiento"),                // Fecha de Nacimiento
                rs.getString("Telefono"),                      // Teléfono
                rs.getString("Direccion"),                     // Dirección
                rs.getInt("Id_Usuario"),                       // ID del Usuario
                rs.getString("Especialidad"),                  // Especialidad
                rs.getBytes("Foto")                            // Foto (byte[])
            );
                // Agregar el doctor a la lista
                list.add(doc);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Imprimir el error para saber qué ocurrió
        } finally {
            // Cerrar los recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (objCon.EstablecerConexion() != null) objCon.EstablecerConexion().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return list;
    }
    public void MostrarTablaDoctor(JTable tabla) {
        
        CConexion objCon = new CConexion();  
        DefaultTableModel modelo = new DefaultTableModel();

        // Definir las columnas de la tabla
        modelo.addColumn("Código");
        modelo.addColumn("Nombres");
        modelo.addColumn("Fecha Nacim");    
        modelo.addColumn("Teléfono");    
        modelo.addColumn("Dirección");    
        modelo.addColumn("Id User");    
        modelo.addColumn("Especialidad");    
        modelo.addColumn("Foto");    

        tabla.setModel(modelo);
        String sql = "SELECT Id_Doctor, Nombre, Fecha_Nacimiento, Telefono, Direccion, Id_Usuario, Especialidad, Foto FROM Doctores";
        Object[] datos = new Object[8]; // Cambiado a Object[]
        byte[] pic;

        try (Connection conn = objCon.EstablecerConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                datos[0] = rs.getString("Id_Doctor");
                datos[1] = rs.getString("Nombre");
                datos[2] = rs.getString("Fecha_Nacimiento");
                datos[3] = rs.getString("Telefono");
                datos[4] = rs.getString("Direccion");
                datos[5] = rs.getString("Id_Usuario");
                datos[6] = rs.getString("Especialidad");
                datos[7] = rs.getBytes("Foto");
                modelo.addRow(datos);
            }
            tabla.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se conectó correctamente, error: " + e.toString());
        } 
    }
    public void InsertarDoctor(JTextField NombDoc,JDateChooser FechaNa,JTextField Tele,JTextField Dire,JTextField IdUser,JTextField Especi,byte[] Fotogr ){
        
        CConexion objc = new CConexion();
        String query = "INSERT INTO Doctores (Nombre, Fecha_Nacimiento, Telefono, Direccion, Id_Usuario, Especialidad, Foto) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try (Connection conn = objc.EstablecerConexion(); PreparedStatement cs = conn.prepareStatement(query)) {
            int idUsuario = Integer.parseInt(IdUser.getText());
            cs.setString(1, NombDoc.getText());

            // Validar la fecha
            if (FechaNa.getDate() != null) {
                cs.setDate(2, new java.sql.Date(FechaNa.getDate().getTime()));
            } else {
                cs.setNull(2, java.sql.Types.DATE);
            }

            cs.setString(3, Tele.getText());
            cs.setString(4, Dire.getText());
            cs.setInt(5, idUsuario);
            cs.setString(6, Especi.getText());

            // Validar y guardar la foto
            if (Fotogr!= null) {
                cs.setBytes(7, Fotogr);
            } else {
                cs.setNull(7, java.sql.Types.BLOB);
            }

            cs.execute();
            JOptionPane.showMessageDialog(null, "Registro de doctor exitoso.");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar: " + e.toString());
        }
//        CConexion objc = new CConexion();
//        String query = "INSERT INTO Doctores (Nombre, Fecha_Nacimiento, Telefono,Direccion, Id_Usuario, Especialidad,Foto) VALUES (?,?,?,?,?,?,?);";
//        try {
////            int idUsuario = obtenerIdUsuarioPorNombre(CodDoc.getText());            
////            if (idUsuario == -1) {
////                JOptionPane.showMessageDialog(null, "Error: Usuario no encontrado.");
////                return;
////            }
//            int idUsuario=Integer.parseInt(IdUser.getText());
//            CallableStatement cs = objc.EstablecerConexion().prepareCall(query);
//            cs.setString(1, NombDoc.getText());
//            if (FechaNa.getDate() != null) {
//            cs.setDate(2, new java.sql.Date(FechaNa.getDate().getTime()));
//            } else {
//                cs.setNull(2, java.sql.Types.DATE);
//            }
//            cs.setString(3, Tele.getText());
//            cs.setString(4, Dire.getText());
//            cs.setInt(5, idUsuario);
//            cs.setString(6, Especi.getText());
//            
//            if (Foto != null) {
//            cs.setBytes(7, Foto); // Asigna el byte[] para la imagen
//            } else {
//                cs.setNull(7, java.sql.Types.BLOB); // Asigna NULL si no hay foto
//            }
////            byte[] img =Foto;
////            ImageIcon imageicon=new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(img.getWidth(),img.getHeight(),Image.SCALE_SMOOTH));
////            Foto.setIcon(imageicon);
//            
//            cs.execute();
//            JOptionPane.showMessageDialog(null, "Registro de doctor exitoso.");
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error al insertar: " + e.toString());
//        }
    }
    
    public void SeleccionarDoctor(JTable tabla, JTextField CodDoc, JTextField NombDoc ,JDateChooser FechaNDoc, 
                                    JTextField TeleDoc, JTextField DireDoc,JTextField IdUseDoc, JTextField Espe, JLabel fotoDoc){
////        try {
////            int fila = tabla.getSelectedRow();
////
////            if (fila >= 0) {
////                // Asignar valores de las columnas a los JTextField
////                CodDoc.setText(tabla.getValueAt(fila, 0).toString());
////                NombDoc.setText(tabla.getValueAt(fila, 1).toString());
////
////                // Formatear y asignar la fecha de nacimiento al JDateChooser
////                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
////                Object fechaNacimientoObj = tabla.getValueAt(fila, 2);
////                if (fechaNacimientoObj != null) {
////                    Date fechaNacimiento = dateFormat.parse(fechaNacimientoObj.toString());
////                    FechaNDoc.setDate(fechaNacimiento);
////                } else {
////                    FechaNDoc.setDate(null);
////                }
////
////                // Asignar otros valores a los JTextField
////                TeleDoc.setText(tabla.getValueAt(fila, 3).toString());
////                DireDoc.setText(tabla.getValueAt(fila, 4).toString());
////                IdUseDoc.setText(tabla.getValueAt(fila, 5).toString());
////                Espe.setText(tabla.getValueAt(fila, 6).toString());
////
////                // Verificar si la columna de la tabla contiene un array de bytes (foto)
////                Object fotoObj = tabla.getValueAt(fila, 7);
////                if (fotoObj != null && fotoObj instanceof byte[]) {
////                    byte[] fotoBytes = (byte[]) fotoObj;
////
////                    // Asignar el array de bytes al parámetro de entrada fotoDoc
////                    System.arraycopy(fotoBytes, 0, fotoDoc, 0, Math.min(fotoBytes.length, fotoDoc.length));
////
////                    // Mostrar la imagen en el componente correspondiente (por ejemplo, un JLabel)
////                    ImageIcon imageIcon = new ImageIcon(fotoBytes);
////                    Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
////
////                    JLabel lblFoto = new JLabel(); // Suponiendo que el componente donde se muestra la foto es un JLabel
////                    lblFoto.setIcon(new ImageIcon(image));
////                } else {
////                    JOptionPane.showMessageDialog(null, "No se encontró foto del doctor.");
////                }
////            } else {
////                JOptionPane.showMessageDialog(null, "Por favor selecciona una fila.");
////            }
////
////        } catch (Exception e) {
////            JOptionPane.showMessageDialog(null, "Error al seleccionar el doctor: " + e.toString());
////        }
        try {
            int fila = tabla.getSelectedRow();

            if (fila >= 0) {
                CodDoc.setText(tabla.getValueAt(fila, 0).toString());
                NombDoc.setText(tabla.getValueAt(fila, 1).toString());

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Object fechaEntregaObj = tabla.getValueAt(fila, 2);
                if (fechaEntregaObj != null) {
                    Date fechanacstr = dateFormat.parse(fechaEntregaObj.toString());
                    FechaNDoc.setDate(fechanacstr);
                } else {
                    FechaNDoc.setDate(null);
                }

                TeleDoc.setText(tabla.getValueAt(fila, 3).toString());
                DireDoc.setText(tabla.getValueAt(fila, 4).toString());
                IdUseDoc.setText(tabla.getValueAt(fila, 5).toString());
                Espe.setText(tabla.getValueAt(fila, 6).toString());

                // Obtener la foto desde la base de datos
                byte[] fotoBytes = (byte[]) tabla.getValueAt(fila, 7);
                if (fotoBytes != null) {
                    ImageIcon imageIcon = new ImageIcon(fotoBytes);
                    Image image = imageIcon.getImage().getScaledInstance(fotoDoc.getWidth(), fotoDoc.getHeight(), Image.SCALE_SMOOTH);
                    fotoDoc.setIcon(new ImageIcon(image));
                } else {
                    fotoDoc.setIcon(null);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor selecciona una fila.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al seleccionar el doctor: " + e.toString());
        }
    }
        
//        try {
//                int fila=tabla.getSelectedRow();
//                
//                if (fila>=0) {
//                    CodDoc.setText(tabla.getValueAt(fila, 0).toString());
//                    NombDoc.setText(tabla.getValueAt(fila, 1).toString());
//                    
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Ajustar al formato de fecha de tu tabla
//                    
//                    Object fechaEntregaObj = tabla.getValueAt(fila, 2);
//                    if (fechaEntregaObj != null) {
//                        String fechanac = fechaEntregaObj.toString();
//                        Date fechanacstr = dateFormat.parse(fechanac);
//                        FechaNDoc.setDate(fechanacstr);
//                    } else {
//                        FechaNDoc.setDate(null);
//                    }
//                    
//                    TeleDoc.setText(tabla.getValueAt(fila, 3).toString());
//                    DireDoc.setText(tabla.getValueAt(fila, 4).toString());
//                    IdUseDoc.setText(tabla.getValueAt(fila, 5).toString());
//                    Espe.setText(tabla.getValueAt(fila, 6).toString());
//                    // Obtener la foto como byte[] y mostrarla en el JLabel
//                    Object fotoObj = tabla.getValueAt(fila, 7);  // Asume que la foto está en la columna 7
//                    if (fotoObj != null && fotoObj instanceof byte[]) {
//                        byte[] fotoBytes = (byte[]) fotoObj;
//
//                        // Convertir byte[] a Image y mostrar en JLabel
//                        ImageIcon imageIcon = new ImageIcon(fotoBytes);
//                        Image image = imageIcon.getImage().getScaledInstance(fotoDoc.getWidth(), fotoDoc.getHeight(), Image.SCALE_SMOOTH);
//                        fotoDoc.setIcon(new ImageIcon(image));
//                    } else {
//                        fotoDoc.setIcon(null);  // En caso de que no haya imagen
//                    }
//                    
//                                     
//                    
//                }
//                else
//                    JOptionPane.showMessageDialog(null, "Error al selecionar, error:");
//            
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e.toString());
//            }
//
//    }

}
