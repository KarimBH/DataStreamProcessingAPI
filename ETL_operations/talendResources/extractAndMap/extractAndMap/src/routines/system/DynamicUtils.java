/**
 * 
 */
package routines.system;

/**
 * @author Administrator
 * 
 */


public class DynamicUtils {

    /**
     * @author parham parvizi
     * @description: write all the values in the dynamic schema to a delimited file writer. fields are separated by the
     * fieldSeparator.
     * @param column the dynamic column to write
     * @param out a java.io.Writer
     * @param fieldSeparator field delimiter
     * @throws java.io.IOException
     */
    public static void writeValuesToDelimitedFile(Dynamic column, java.io.Writer out, String fieldSeparator)
            throws java.io.IOException {
        for (int i = 0; i < column.getColumnCount(); i++) {
        	if (column.getColumnValue(i) != null ) {
        		DynamicMetadata metadata = column.getColumnMetadata(i);
	        	if("id_Date".equals(metadata.getType()) && !(DBMSConstants.MSSQL.getDBmsId().equalsIgnoreCase(column.getDbmsId()) && !(metadata.getDbType().toLowerCase().indexOf("timestamp") < 0))) {
	        		out.write(FormatterUtils.format_Date((java.util.Date)column.getColumnValue(i),metadata.getFormat()));
	        	} else {
	        		out.write(String.valueOf(column.getColumnValue(i)));
	        	}
        	}
            if (i != (column.getColumnCount() - 1))
                out.write(fieldSeparator);
        }
        out.flush();
    }
    
	public static void writeValuesToStringBuilder(Dynamic column, Appendable sb, String fieldSeparator) throws java.io.IOException {
		for (int i = 0; i < column.getColumnCount(); i++) {
			if (column.getColumnValue(i) != null ) {
				DynamicMetadata metadata = column.getColumnMetadata(i);
				if ("id_Date".equals(metadata.getType()) && !(DBMSConstants.MSSQL.getDBmsId().equalsIgnoreCase(column.getDbmsId()) && !(metadata.getDbType().toLowerCase().indexOf("timestamp") < 0))) {
					sb.append(FormatterUtils.format_Date((java.util.Date) column.getColumnValue(i),metadata.getFormat()));
				} else {
					sb.append(String.valueOf(column.getColumnValue(i)));
				}
			}
			if (i != (column.getColumnCount() - 1))
				sb.append(fieldSeparator);
		}
	}

	public static void writeValuesToStringArray(Dynamic column, String[] row, int offset) {
		for (int i = 0; i < column.getColumnCount(); i++) {
			if (column.getColumnValue(i) != null) {
				DynamicMetadata metadata = column.getColumnMetadata(i);
				if ("id_Date".equals(metadata.getType()) && !(DBMSConstants.MSSQL.getDBmsId().equalsIgnoreCase(column.getDbmsId()) && !(metadata.getDbType().toLowerCase().indexOf("timestamp") < 0))) {
					row[offset + i] = FormatterUtils.format_Date((java.util.Date) column.getColumnValue(i),metadata.getFormat());
				} else {
					row[offset + i] = String.valueOf(column.getColumnValue(i));
				}
			} else {
				row[offset + i] = "";
			}
		}
	}
    
    public static void writeHeaderToDelimitedFile(Dynamic column, java.io.Writer out, String fieldSeparator)
            throws java.io.IOException {
        for (int i = 0; i < column.getColumnCount(); i++) {
            out.write(column.getColumnMetadata(i).getName());
            if (i != (column.getColumnCount() - 1))
                out.write(fieldSeparator);
        }
        out.flush();
    }

    public static void readColumnsFromDelimitedFile(Dynamic column, org.talend.fileprocess.FileInputDelimited fid,
            int fixedColumnCount) throws Exception {
        int fieldCount = fid.getColumnsCountOfCurrentRow();
        for (int i = 0; i < column.getColumnCount(); i++) {
            if ((fixedColumnCount + i) < fieldCount)
                column.addColumnValue(fid.get((fixedColumnCount + i)));
            else
                column.addColumnValue("");
        }
    }

    public static void readColumnsFromDatabase(Dynamic column, java.sql.ResultSet rs, int fixedColumnCount) throws Exception {
        column.clearColumnValues();
        for (int i = 0; i < column.getColumnCount(); i++) {
            DynamicMetadata dcm = column.getColumnMetadata(i);

            if ("id_String".equals(dcm.getType())) {
                column.addColumnValue(rs.getString(fixedColumnCount+i+1));
            } else if ("id_Date".equals(dcm.getType())) {
                if (DBMSConstants.MSSQL.getDBmsId().equalsIgnoreCase(column.getDbmsId())
                        && !(dcm.getDbType().toLowerCase().indexOf("timestamp") < 0)) {
                    column.addColumnValue(rs.getString(fixedColumnCount+i+1));
                } else if(DBMSConstants.NETEZZA.getDBmsId().equalsIgnoreCase(column.getDbmsId())
                        && "time".equalsIgnoreCase(dcm.getDbType())){
                    column.addColumnValue(rs.getTime(fixedColumnCount+i+1));
                } else {
                    column.addColumnValue(rs.getTimestamp(fixedColumnCount+i+1));
                }
            } else if ("id_Integer".equals(dcm.getType()) || "id_Long".equals(dcm.getType()) || "id_Double".equals(dcm.getType())|| "id_Byte".equals(dcm.getType())) {
                if (rs.getObject(fixedColumnCount+i+1) == null) {
                    column.addColumnValue(null);
                    continue;
                }
                if ("id_Integer".equals(dcm.getType())) {
                    column.addColumnValue(rs.getInt(fixedColumnCount+i+1));
                } else if ("id_Long".equals(dcm.getType())) {
                    column.addColumnValue(rs.getLong(fixedColumnCount+i+1));
                } else if ("id_Double".equals(dcm.getType())) {
                    column.addColumnValue(rs.getDouble(fixedColumnCount+i+1));
                }else if("id_Byte".equals(dcm.getType())){
                	column.addColumnValue(rs.getByte(fixedColumnCount+i+1));
                }
            } else {
                column.addColumnValue(rs.getObject(fixedColumnCount+i+1));
            }
        }
    }
    
    public static void readColumnsFromDatabase_Access(Dynamic column, java.sql.ResultSet rs, int fixedColumnCount) throws Exception {
        column.clearColumnValues();
        for (int i = 0; i < column.getColumnCount(); i++) {
            DynamicMetadata dcm = column.getColumnMetadata(i);
            if ("id_String".equals(dcm.getType())) {
                column.addColumnValue(rs.getString(fixedColumnCount+i+1));
            } else if ("id_Date".equals(dcm.getType())) {
                column.addColumnValue(rs.getTimestamp(fixedColumnCount+i+1));
            } else if ("id_Byte".equals(dcm.getType()) || "id_Short".equals(dcm.getType()) || "id_Integer".equals(dcm.getType()) || "id_Long".equals(dcm.getType()) || "id_Float".equals(dcm.getType()) || "id_Double".equals(dcm.getType()) ) {
                Object obj = rs.getObject(fixedColumnCount+i+1);
                if (obj== null) {
                    column.addColumnValue(null);
                    continue;
                }
                if("id_Byte".equals(dcm.getType())){
                    column.addColumnValue(Byte.parseByte(obj.toString()));
                }else if ("id_Short".equals(dcm.getType())) {
                    column.addColumnValue(Short.parseShort(obj.toString()));
                }else if ("id_Integer".equals(dcm.getType())) {
                    column.addColumnValue(Integer.parseInt(obj.toString()));
                } else if ("id_Long".equals(dcm.getType())) {
                    column.addColumnValue(Long.parseLong(obj.toString()));
                } else if ("id_Float".equals(dcm.getType())) {
                    column.addColumnValue(Float.parseFloat(obj.toString()));
                } else if ("id_Double".equals(dcm.getType())) {
                    column.addColumnValue(Double.parseDouble(obj.toString()));
                }
            } else {
                column.addColumnValue(rs.getObject(fixedColumnCount+i+1));
            }
        }
    }
    
    public static void readColumnsFromDatabase_Mssql(Dynamic column, java.sql.ResultSet rs, int fixedColumnCount,java.util.List<Object> list) throws Exception {
        column.clearColumnValues();
        for (int i = 0; i < column.getColumnCount(); i++) {
            DynamicMetadata dcm = column.getColumnMetadata(i);

            if ("id_String".equals(dcm.getType())) {
            	if(DBMSConstants.MSSQL.getDBmsId().equalsIgnoreCase(column.getDbmsId()) && "NTEXT".equals(dcm.getDbType().toUpperCase())){
                	column.addColumnValue(list.get(0)); list.remove(0);
            	} else {
            		column.addColumnValue(rs.getString(fixedColumnCount+i+1));
            	}
            } else if ("id_Date".equals(dcm.getType())) {
                if (DBMSConstants.MSSQL.getDBmsId().equalsIgnoreCase(column.getDbmsId())
                        && !(dcm.getDbType().toLowerCase().indexOf("timestamp") < 0)) {
                    column.addColumnValue(rs.getString(fixedColumnCount+i+1));
                } else {
                    column.addColumnValue(rs.getTimestamp(fixedColumnCount+i+1));
                }
            } else if ("id_Integer".equals(dcm.getType()) || "id_Long".equals(dcm.getType()) || "id_Double".equals(dcm.getType())) {
                if (rs.getObject(fixedColumnCount+i+1) == null) {
                    column.addColumnValue(null);
                    continue;
                }
                if ("id_Integer".equals(dcm.getType())) {
                    column.addColumnValue(rs.getInt(fixedColumnCount+i+1));
                } else if ("id_Long".equals(dcm.getType())) {
                    column.addColumnValue(rs.getLong(fixedColumnCount+i+1));
                } else if ("id_Double".equals(dcm.getType())) {
                    column.addColumnValue(rs.getDouble(fixedColumnCount+i+1));
                }
            } else {
                column.addColumnValue(rs.getObject(fixedColumnCount+i+1));
            }
        }
    }

    public static int writeColumnsToDatabse(Dynamic column, java.sql.PreparedStatement pstmt, int fixedColumnCount,String database)
            throws Exception {

        for (int i = 0; i < column.getColumnCount(); i++) {
            // DynamicColumnMetadata dcm = column.getColumnMetadata(i);
            Object value = column.getColumnValue(i);

            if (value == null) {
            	if(DBMSConstants.SYBASE.getDBmsId().equalsIgnoreCase(database)) {
            		pstmt.setNull((fixedColumnCount + i + 1), java.sql.Types.VARCHAR);
            	}else if(DBMSConstants.MSSQL.getDBmsId().equalsIgnoreCase(database)) {
					if ("id_byte[]".equals(column.getColumnMetadata(i).getType())){
						pstmt.setNull(fixedColumnCount + i + 1,java.sql.Types.BINARY);
					}
					else{
						pstmt.setNull((fixedColumnCount + i + 1),java.sql.Types.NULL);
					}
            	} else {
            		pstmt.setNull((fixedColumnCount + i + 1), java.sql.Types.NULL);
            	}
            }
            else if ("id_Integer".equals(column.getColumnMetadata(i).getType()))
                pstmt.setInt(fixedColumnCount + i + 1, Integer.valueOf(value.toString()));
            else if ("id_String".equals(column.getColumnMetadata(i).getType()))
                pstmt.setString((fixedColumnCount + i + 1), String.valueOf(value));
            else if ("id_Double".equals(column.getColumnMetadata(i).getType()))
                pstmt.setDouble((fixedColumnCount + i + 1), Double.valueOf(value.toString()));
            else if ("id_Float".equals(column.getColumnMetadata(i).getType()))
                pstmt.setFloat((fixedColumnCount + i + 1), Float.valueOf(value.toString()));
            else if ("id_Long".equals(column.getColumnMetadata(i).getType()))
                pstmt.setLong((fixedColumnCount + i + 1), Long.valueOf(value.toString()));
            else if ("id_BigDecimal".equals(column.getColumnMetadata(i).getType()))
                pstmt.setBigDecimal((fixedColumnCount + i + 1), new java.math.BigDecimal(value.toString()));
            else if ("id_Boolean".equals(column.getColumnMetadata(i).getType()))
                pstmt.setBoolean((fixedColumnCount + i + 1), Boolean.valueOf(value.toString()));
            else if ("id_Byte".equals(column.getColumnMetadata(i).getType()))
                pstmt.setByte((fixedColumnCount + i + 1), Byte.valueOf(value.toString()));
            else if ("id_Short".equals(column.getColumnMetadata(i).getType()))
                pstmt.setShort((fixedColumnCount + i + 1), Short.valueOf(value.toString()));
            else if ("id_Date".equals(column.getColumnMetadata(i).getType())
                    || "id_Time".equals(column.getColumnMetadata(i).getType())
                    || "id_Timestamp".equals(column.getColumnMetadata(i).getType())) {
            	String formatValue = FormatterUtils.format_Date((java.util.Date)value, "yyyy-MM-dd HH:mm:ss.SSS");
            	pstmt.setTimestamp((fixedColumnCount + i + 1), java.sql.Timestamp.valueOf(formatValue));
            }
            else if ("id_Blob".equals(column.getColumnMetadata(i).getType()))
                pstmt.setBlob((fixedColumnCount + i + 1), (java.sql.Blob) value);
            else if ("id_Clob".equals(column.getColumnMetadata(i).getType()))
                pstmt.setClob((fixedColumnCount + i + 1), (java.sql.Clob) value);
            else
                pstmt.setObject((fixedColumnCount + i + 1), value);

        }
        return column.getColumnCount();
    }

    public static String getCreateTableSQL(Dynamic column, String dbmsId) {
        DBManager manager = DBManagerFactory.getDBManager(dbmsId);
        String str = manager.getCreateTableSQL(column);
        return str;
    }

    public static String getInsertIntoStmtColumnsList(Dynamic column, String database) {
        DBManager manager = DBManagerFactory.getDBManager(database);
        String str = manager.getInsertTableSQL(column);
        return str;
    }

    public static String getInsertIntoStmtValuesList(Dynamic column) {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < column.getColumnCount(); i++) {
            if (i < (column.getColumnCount() - 1))
                list.append("?,");
            else
                list.append("?");
        }
        return list.toString();
    }

    public static String getUpdateSet(Dynamic column, String dbmsId) {
        DBManager manager = DBManagerFactory.getDBManager(dbmsId);
        String updateSqlSet = manager.getUpdateSetSQL(column);
        return updateSqlSet;
    }
}
