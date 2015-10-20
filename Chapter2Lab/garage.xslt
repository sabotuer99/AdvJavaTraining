<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    version="1.0">
  <xsl:template match="/">
    <html><body><div align="center">
      <table border="1">
        <xsl:apply-templates select="garage/*"/>
      </table>
    </div></body></html>
  </xsl:template>

  <xsl:template match="car|van">
    <tr>
      <td><xsl:value-of select="year"/></td>
      <td><xsl:value-of select="make"/></td>
      <td><xsl:value-of select="model"/></td>
    </tr>
  </xsl:template>
</xsl:stylesheet>

