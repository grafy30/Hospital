package Hospital.Dao;

import Hospital.Conexion.CConexion;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Date;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
/**
 * @author Wilson
 */
public class DoctorDAO {
    
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
            
            // Obtener la foto como bytes
            pic = rs.getBytes("Foto");
            // Convertir bytes a ImageIcon
            ImageIcon imageIcon = null;
            if (pic != null) {
                imageIcon = new ImageIcon(pic);
                // Escalar la imagen si es necesario
                Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Cambia 50, 50 por el tamaño que desees
                imageIcon = new ImageIcon(image);
            }

            // Agregar la imagen al arreglo de datos
            datos[7] = imageIcon; // Se asigna el ImageIcon directamente
                             
            modelo.addRow(datos);
        }
        tabla.setModel(modelo);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se conectó correctamente, error: " + e.toString());
    } 
//        CConexion objCon=new CConexion();  
//        DefaultTableModel modelo=new DefaultTableModel();
//        String sql="";
//        modelo.addColumn("Codigo");
//        modelo.addColumn("Nombres");
//        modelo.addColumn("Fecha Nacim");    
//        modelo.addColumn("Telefono");    
//        modelo.addColumn("Direccion");    
//        modelo.addColumn("Id User");    
//        modelo.addColumn("Especialidad");    
//        modelo.addColumn("Foto");    
//        
//        tabla.setModel(modelo);
//        sql="SELECT Id_Doctor,Nombre,Fecha_Nacimiento,Telefono,Direccion,Id_Usuario,Especialidad,Foto FROM Doctores";
//        Object [] datos=new String[8];
//        byte[] pic;
//        Statement st;
//        try {
//            st= objCon.EstablecerConexion().createStatement();
//            ResultSet rs=st.executeQuery(sql);
//            while (rs.next()) {
//                datos[0]=rs.getString(1);
//                datos[1]=rs.getString(2);
//                datos[2]=rs.getString(3);
//                datos[3]=rs.getString(4);
//                datos[4]=rs.getString(5);
//                datos[5]=rs.getString(6);
//                datos[6]=rs.getString(7);
//
//                
//                pic=rs.getBytes("Foto");
//                // Convertir bytes a ImageIcon
//                ImageIcon imageIcon = null;
//                if (pic != null) {
//                    imageIcon = new ImageIcon(pic);
//                    // Escalar la imagen si es necesario
//                    Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Cambia 50, 50 por el tamaño que desees
//                    imageIcon = new ImageIcon(image);
//                }
//
//                // Agregar la imagen al arreglo de datos
//                datos[7] = imageIcon; // Se asigna el ImageIcon directamente
//                             
//                modelo.addRow(datos);
//            }
//            tabla.setModel(modelo);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "No se conecto correctamente, error:"+e.toString());
//        }  
    }
    public void InsertarDoctor(JTextField NombDoc,JDateChooser FechaNa,JTextField Tele,JTextField Dire,JTextField IdUser,JTextField Especi,byte[] Foto ){
        
        CConexion objc = new CConexion();
        String query = "INSERT INTO Doctores (Nombre, Fecha_Nacimiento, Telefono,Direccion, Id_Usuario, Especialidad,Foto) VALUES (?,?,?,?,?,?,?);";
        try {
//            int idUsuario = obtenerIdUsuarioPorNombre(CodDoc.getText());            
//            if (idUsuario == -1) {
//                JOptionPane.showMessageDialog(null, "Error: Usuario no encontrado.");
//                return;
//            }
            int idUsuario=Integer.parseInt(IdUser.getText());
            CallableStatement cs = objc.EstablecerConexion().prepareCall(query);
            cs.setString(1, NombDoc.getText());
            if (FechaNa.getDate() != null) {
            cs.setDate(2, new java.sql.Date(FechaNa.getDate().getTime()));
            } else {
                cs.setNull(2, java.sql.Types.DATE);
            }
            cs.setString(3, Tele.getText());
            cs.setString(4, Dire.getText());
            cs.setInt(5, idUsuario);
            cs.setString(6, Especi.getText());
            
            if (Foto != null) {
            cs.setBytes(7, Foto); // Asigna el byte[] para la imagen
            } else {
                cs.setNull(7, java.sql.Types.BLOB); // Asigna NULL si no hay foto
            }
//            byte[] img =Foto;
//            ImageIcon imageicon=new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(img.getWidth(),img.getHeight(),Image.SCALE_SMOOTH));
//            Foto.setIcon(imageicon);
            
            cs.execute();
            JOptionPane.showMessageDialog(null, "Registro de doctor exitoso.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar: " + e.toString());
        }
    }
    
    public void SeleccionarDoctor(JTable tabla, JTextField CodDoc, JTextField NombDoc ,JDateChooser FechaNDoc, 
                                    JTextField TeleDoc, JTextField DireDoc,JTextField IdUseDoc, JTextField Espe, JLabel fotoDoc){
        
        try {
                int fila=tabla.getSelectedRow();
                
                if (fila>=0) {
                    CodDoc.setText(tabla.getValueAt(fila, 0).toString());
                    NombDoc.setText(tabla.getValueAt(fila, 1).toString());
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Ajustar al formato de fecha de tu tabla
                    
                    Object fechaEntregaObj = tabla.getValueAt(fila, 2);
                    if (fechaEntregaObj != null) {
                        String fechanac = fechaEntregaObj.toString();
                        Date fechanacstr = dateFormat.parse(fechanac);
                        FechaNDoc.setDate(fechanacstr);
                    } else {
                        FechaNDoc.setDate(null);
                    }
                    
                    TeleDoc.setText(tabla.getValueAt(fila, 3).toString());
                    DireDoc.setText(tabla.getValueAt(fila, 4).toString());
                    IdUseDoc.setText(tabla.getValueAt(fila, 5).toString());
                    Espe.setText(tabla.getValueAt(fila, 6).toString());
                    // Obtener la foto como byte[] y mostrarla en el JLabel
                    Object fotoObj = tabla.getValueAt(fila, 7);  // Asume que la foto está en la columna 7
                    if (fotoObj != null && fotoObj instanceof byte[]) {
                        byte[] fotoBytes = (byte[]) fotoObj;

                        // Convertir byte[] a Image y mostrar en JLabel
                        ImageIcon imageIcon = new ImageIcon(fotoBytes);
                        Image image = imageIcon.getImage().getScaledInstance(fotoDoc.getWidth(), fotoDoc.getHeight(), Image.SCALE_SMOOTH);
                        fotoDoc.setIcon(new ImageIcon(image));
                    } else {
                        fotoDoc.setIcon(null);  // En caso de que no haya imagen
                    }
                    
                                     
                    
                }
                else
                    JOptionPane.showMessageDialog(null, "Error al selecionar, error:");
            
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }

    }

}
